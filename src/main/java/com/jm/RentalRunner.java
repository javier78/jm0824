package com.jm;

import com.jm.service.RentalService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class RentalRunner {
    RentalService rentalService;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine());
    }

    public void checkoutRental(String input) throws Exception {
        rentalService.generateRentalAgreement(input);
    }
}
