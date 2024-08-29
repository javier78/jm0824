package com.jm;

import com.jm.service.HolidayService;
import com.jm.service.HolidayServiceImpl;
import com.jm.service.RentalService;
import com.jm.service.RentalServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class RentalRunner {
    static RentalService rentalService;
    static HolidayService holidayService;

    public static void main(String[] args) throws Exception {
        holidayService = new HolidayServiceImpl();
        rentalService = new RentalServiceImpl(holidayService);
        Scanner sc = new Scanner(System.in);
        rentalService.generateRentalAgreement(sc.nextLine());
    }
}
