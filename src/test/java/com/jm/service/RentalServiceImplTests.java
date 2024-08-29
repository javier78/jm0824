package com.jm.service;


import com.jm.enums.ToolTypes;
import com.jm.enums.Tools;
import com.jm.model.RentalAgreement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentalServiceImplTests {

    static RentalServiceImpl rentalService;
    static HolidayServiceImpl holidayService;

    @BeforeAll
    public static void setUp() {
        holidayService = new HolidayServiceImpl();
        rentalService = new RentalServiceImpl(holidayService);
    }

    @Test
    public void testGetRentalAgreementWith101DiscountTest1() throws Exception {
        Exception e = assertThrows(Exception.class, () -> rentalService.generateRentalAgreement("JAKR 5 101 9/3/15"));
        assertEquals("Discount percent must be within 0-100", e.getMessage());
    }

    @Test
    public void testGetRentalAgreementTest2() throws Exception {
        RentalAgreement actual = rentalService.generateRentalAgreement("LADW 3 10 7/2/20");
        RentalAgreement expected = RentalAgreement.builder().toolCode(Tools.LADW.name())
                .toolType(ToolTypes.LADDER.name()).toolBrand(Tools.LADW.getBrand()).rentalDays(3)
                .checkoutDate(LocalDate.of(2020, 7, 2))
                .dueDate(LocalDate.of(2020, 7, 5))
                .dailyRentalCharge(ToolTypes.LADDER.getDailyCharge())
                .chargeDays(2).prediscountCharge(new BigDecimal("3.98")).discountPercent(10).discountAmount(new BigDecimal(".40")).finalCharge(new BigDecimal("3.58")).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetRentalAgreementTest3() throws Exception {
        RentalAgreement actual = rentalService.generateRentalAgreement("CHNS 5 25 7/2/15");
        RentalAgreement expected = RentalAgreement.builder().toolCode(Tools.CHNS.name())
                .toolType(ToolTypes.CHAINSAW.name()).toolBrand(Tools.CHNS.getBrand()).rentalDays(5)
                .checkoutDate(LocalDate.of(2015, 7, 2))
                .dueDate(LocalDate.of(2015, 7, 7))
                .dailyRentalCharge(ToolTypes.CHAINSAW.getDailyCharge())
                .chargeDays(3).prediscountCharge(new BigDecimal("4.47")).discountPercent(25).discountAmount(new BigDecimal("1.12")).finalCharge(new BigDecimal("3.35")).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetRentalAgreementTest4() throws Exception {
        RentalAgreement actual = rentalService.generateRentalAgreement("JAKD 6 0 9/3/15");
        RentalAgreement expected = RentalAgreement.builder().toolCode(Tools.JAKD.name())
                .toolType(ToolTypes.JACKHAMMER.name()).toolBrand(Tools.JAKD.getBrand()).rentalDays(6)
                .checkoutDate(LocalDate.of(2015, 9, 3))
                .dueDate(LocalDate.of(2015, 9, 9))
                .dailyRentalCharge(ToolTypes.JACKHAMMER.getDailyCharge())
                .chargeDays(3).prediscountCharge(new BigDecimal("8.97")).discountPercent(0).discountAmount(new BigDecimal("0.00")).finalCharge(new BigDecimal("8.97")).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetRentalAgreementTest5() throws Exception {
        RentalAgreement actual = rentalService.generateRentalAgreement("JAKR 9 0 7/2/15");
        RentalAgreement expected = RentalAgreement.builder().toolCode(Tools.JAKR.name())
                .toolType(ToolTypes.JACKHAMMER.name()).toolBrand(Tools.JAKR.getBrand()).rentalDays(9)
                .checkoutDate(LocalDate.of(2015, 7, 2))
                .dueDate(LocalDate.of(2015, 7, 11))
                .dailyRentalCharge(ToolTypes.JACKHAMMER.getDailyCharge())
                .chargeDays(5).prediscountCharge(new BigDecimal("14.95")).discountPercent(0).discountAmount(new BigDecimal("0.00")).finalCharge(new BigDecimal("14.95")).build();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRentalAgreementTest6() throws Exception {
        RentalAgreement actual = rentalService.generateRentalAgreement("JAKR 4 50 7/2/20");
        RentalAgreement expected = RentalAgreement.builder().toolCode(Tools.JAKR.name())
                .toolType(ToolTypes.JACKHAMMER.name()).toolBrand(Tools.JAKR.getBrand()).rentalDays(4)
                .checkoutDate(LocalDate.of(2020, 7, 2))
                .dueDate(LocalDate.of(2020, 7, 6))
                .dailyRentalCharge(ToolTypes.JACKHAMMER.getDailyCharge())
                .chargeDays(1).prediscountCharge(new BigDecimal("2.99")).discountPercent(50).discountAmount(new BigDecimal("1.50")).finalCharge(new BigDecimal("1.49")).build();

        assertEquals(expected, actual);
    }
}
