package com.jm.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface HolidayService {

    List<LocalDate> getHolidaysForYear(int year);
}
