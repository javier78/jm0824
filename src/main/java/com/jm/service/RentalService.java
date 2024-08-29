package com.jm.service;

import com.jm.model.RentalAgreement;

public interface RentalService {

    RentalAgreement generateRentalAgreement(String checkoutInput) throws Exception;

}
