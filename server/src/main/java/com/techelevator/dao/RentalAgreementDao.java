package com.techelevator.dao;

import com.techelevator.model.RentalAgreement;

import java.util.List;

public interface RentalAgreementDao {
    List<RentalAgreement> getAllRentalAgreements();

    RentalAgreement getRentalAgreementById(int id);

    RentalAgreement createRentalAgreement(RentalAgreement newRentalAgreement);

    boolean updateRentalAgreement(RentalAgreement updatedRentalAgreement);

    boolean deleteRentalAgreement(int id);

    List<RentalAgreement> getRentalAgreementsByRenterId(int renterId);

    List<RentalAgreement> getRentalAgreementsByPropertyId(int propertyId);

}
