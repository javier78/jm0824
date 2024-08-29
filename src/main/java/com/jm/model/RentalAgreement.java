package com.jm.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private Integer rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private Integer chargeDays;
    private BigDecimal prediscountCharge;
    private Integer discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public String toString() {
        final DateTimeFormatter toStringFormat = DateTimeFormatter.ofPattern("M/d/yy");

        StringBuilder sb = new StringBuilder();
        sb.append("Tool code: ");
        sb.append(toolCode).append("\n");
        sb.append("Tool type: ");
        sb.append(toolType).append("\n");
        sb.append("Tool brand: ");
        sb.append(toolBrand).append("\n");
        sb.append("Rental days: ");
        sb.append(rentalDays).append("\n");
        sb.append("Checkout Date: ");
        sb.append(checkoutDate.format(toStringFormat)).append("\n");
        sb.append("Due Date: ");
        sb.append(dueDate.format(toStringFormat)).append("\n");
        sb.append("Daily rental charge: ");
        sb.append(NumberFormat.getCurrencyInstance().format(dailyRentalCharge)).append("\n");
        sb.append("Charge days: ");
        sb.append(chargeDays).append("\n");
        sb.append("Pre-discount charge: ");
        sb.append(NumberFormat.getCurrencyInstance().format(prediscountCharge)).append("\n");
        sb.append("Discount percent: ");
        sb.append(discountPercent).append("%").append("\n");
        sb.append("Discount amount: ");
        sb.append(NumberFormat.getCurrencyInstance().format(discountAmount)).append("\n");
        sb.append("Final charge: ");
        sb.append(NumberFormat.getCurrencyInstance().format(finalCharge)).append("\n");
        return sb.toString();
    }
}
