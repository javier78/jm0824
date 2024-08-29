package com.jm.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class HolidayServiceImpl implements HolidayService {

    /**
     * Returns a list of {@link LocalDate} that represent the holidays for the given year
     * @param year The year to generate holiday dates for
     * @return The list of holiday dates
     */
    @Override
    public List<LocalDate> getHolidaysForYear(int year) {

        List<LocalDate> holidays = new ArrayList<>();

        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        LocalDate laborDay = LocalDate.of(year, Month.SEPTEMBER, 1).with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));

        if(independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if(independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        holidays.add(independenceDay);
        holidays.add(laborDay);
        return holidays;
    }
}
