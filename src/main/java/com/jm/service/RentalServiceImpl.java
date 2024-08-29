package com.jm.service;


import com.jm.enums.ToolTypes;
import com.jm.enums.Tools;
import com.jm.model.RentalAgreement;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final HolidayService holidayService;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("M/d/yy");

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    /**
     * Generates a {@link RentalAgreement} object containing the input string, and calculated val
     * @param checkoutInput The input from the spec: toolCode rentalDayCount discountPercent checkoutDate
     * @return A populated RentalAgreement object
     * @throws Exception in the case where there were input errors
     */
    @Override
    public RentalAgreement generateRentalAgreement(String checkoutInput) throws Exception {
        String[] input = checkoutInput.split(" ");
        Tools tool = Tools.valueOf(input[0]);
        int rentalDayCount = Integer.parseInt(input[1]);
        BigDecimal discountPercent = new BigDecimal(input[2]);
        LocalDate checkoutDate = LocalDate.parse(input[3], DATE_TIME_FORMATTER);
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);
        int chargeDays = getChargeableDays(tool.getToolType(), checkoutDate, dueDate);

        BigDecimal preDiscountCharge = tool.getToolType().getDailyCharge().multiply(new BigDecimal(chargeDays));

        BigDecimal discountAmount = preDiscountCharge.multiply(discountPercent.divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);

        if (rentalDayCount < 1) {
            throw new Exception("Rental day count must be greater than 0");
        } else if(discountPercent.compareTo(BigDecimal.ZERO) < 0 || discountPercent.compareTo(ONE_HUNDRED) > 0) {
            throw new Exception("Discount percent must be within 0-100");
        }

        RentalAgreement agreement = RentalAgreement.builder().toolCode(tool.name()).toolType(tool.getToolType().name())
                .toolBrand(tool.getBrand()).rentalDays(rentalDayCount).checkoutDate(checkoutDate)
                .dueDate(dueDate).dailyRentalCharge(tool.getToolType().getDailyCharge()).chargeDays(chargeDays)
                .prediscountCharge(preDiscountCharge).discountPercent(discountPercent.intValue())
                .discountAmount(discountAmount)
                .finalCharge(preDiscountCharge.subtract(discountAmount))
                .build();
        System.out.println(agreement);

        return agreement;
    }

    /**
     * Given a tool type, a checkout date and a due date, calculate the number of chargeable days  within that date range
     * @param toolType The tool type enum to base the charge day logic on
     * @param checkoutDate the start date, non-inclusive
     * @param dueDate the end date, inclusive
     * @return The number of charge days based on the tool type
     */
    int getChargeableDays(ToolTypes toolType, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeableDays = dueDate.compareTo(checkoutDate);

        if(!toolType.getHolidayCharge()) {
            List<LocalDate> holidays = holidayService.getHolidaysForYear(checkoutDate.getYear());
            for(LocalDate holiday : holidays) {
                if(holiday.isAfter(checkoutDate) && holiday.isBefore(dueDate) || holiday.equals(dueDate)) {
                    chargeableDays--;
                }
            }
        }

        if(!toolType.getWeekendCharge()) {
            LocalDate iterator = checkoutDate.plusDays(1);
            while(!iterator.isAfter(dueDate)) {
                if(iterator.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    chargeableDays --;
                    iterator = iterator.plusDays(1);
                } else if(iterator.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    chargeableDays--;
                    iterator = iterator.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                } else {
                    iterator = iterator.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
                }
            }
        }
        return chargeableDays;
    }
}
