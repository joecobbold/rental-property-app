package com.techelevator.controller;


import com.techelevator.dao.RentalAgreementDao;
import com.techelevator.dao.RenterDao;
import com.techelevator.model.RentalAgreement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/rental_agreements")
public class RentalAgreementsController {

    private final RentalAgreementDao rentalAgreementDao;
    private final RenterDao renterDao;

    public RentalAgreementsController(RentalAgreementDao rentalAgreementDao, RenterDao renterDao) {
        this.rentalAgreementDao = rentalAgreementDao;
        this.renterDao = renterDao;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public RentalAgreement getRentalAgreementById(@PathVariable Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID cannot be null");
        }
        RentalAgreement rentalAgreement = rentalAgreementDao.getRentalAgreementById(id);
        if (rentalAgreement == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No rental agreements found for ID: " + id);
        }
        return rentalAgreement;
    }



//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/{id}")
//    //@RequestMapping(path="/renter/{renterId}/rental_agreement")
//    public RentalAgreement getRentalAgreementById(Integer id){
//        RentalAgreement rentalAgreement = rentalAgreementDao.getRentalAgreementById(id);
//        if (rentalAgreement == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No rental agreements found for ID: " + id);
//        }
//        return rentalAgreement;
//    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/renter/{renterId}")
    //@RequestMapping(path="/renter/{renterId}/rental_agreement")
    public List<RentalAgreement> getRentalAgreementsByRenterId(@PathVariable int renterId){
        List<RentalAgreement> agreements = rentalAgreementDao.getRentalAgreementsByRenterId(renterId);
        if (agreements.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No rental agreements found for renter ID: " + renterId);
        }
        return agreements;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/property/{propertyId}")
    //@RequestMapping(path="/property/{propertyId}/rental_agreement")
    public List<RentalAgreement> getRentalAgreementsByPropertyId(@PathVariable int propertyId){
        List<RentalAgreement> agreements = rentalAgreementDao.getRentalAgreementsByPropertyId(propertyId);
        if (agreements.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No rental agreements found for property ID: " + propertyId);
        }
        return agreements;
    }



    //Just added need api endpoints for this
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalAgreement createRentalAgreement(@Valid @RequestBody RentalAgreement newRentalAgreement) {
        try {
            rentalAgreementDao.createRentalAgreement(newRentalAgreement);
            return newRentalAgreement;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating rental agreement", e);
        }
    }


    //Just added need api endpoints for this
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public RentalAgreement updateRentalAgreement(@PathVariable int id, @Valid @RequestBody RentalAgreement updatedRentalAgreement) {
        updatedRentalAgreement.setRental_agreement_id(id);
        RentalAgreement existingAgreement = rentalAgreementDao.getRentalAgreementById(id);
        if (existingAgreement == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental agreement not found with ID: " + id);
        }
        try {
            rentalAgreementDao.updateRentalAgreement(updatedRentalAgreement);
            return updatedRentalAgreement;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating rental agreement", e);
        }
    }

    //Just added need api endpoints for this
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRentalAgreement(@PathVariable int id) {
        RentalAgreement existingAgreement = rentalAgreementDao.getRentalAgreementById(id);
        if (existingAgreement == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental agreement not found with ID: " + id);
        }
        try {
            rentalAgreementDao.deleteRentalAgreement(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting rental agreement", e);
        }

    }
}
