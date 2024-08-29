package com.jm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum ToolTypes {
    LADDER(new BigDecimal("1.99"), true, true, false),
    CHAINSAW(new BigDecimal("1.49"), true, false, true),
    JACKHAMMER(new BigDecimal("2.99"), true, false, false);

    private final BigDecimal dailyCharge;
    private final Boolean weekdayCharge;
    private final Boolean weekendCharge;
    private final Boolean holidayCharge;

}
