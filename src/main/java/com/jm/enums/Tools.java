package com.jm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tools {
    CHNS(ToolTypes.CHAINSAW, "Stihl"),
    LADW(ToolTypes.LADDER, "Werner"),
    JAKD(ToolTypes.JACKHAMMER, "DeWalt"),
    JAKR(ToolTypes.JACKHAMMER, "Ridgid");

    private final ToolTypes toolType;
    private final String brand;
}
