package com.techelevator.controller;

import com.techelevator.dao.RenterDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Renter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/renter")
@PreAuthorize("isAuthenticated()")
public class RentersController {


    private final RenterDao renterDao;

    public RentersController(RenterDao renterDao) {
        this.renterDao = renterDao;
    }



    @GetMapping
    public List<Renter> getAllRenters(){
        try {
            return renterDao.getAllRenters();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching renters", e);
        }
    }


    @GetMapping(path="/{id}")
    public Renter getRenterById(@PathVariable int id){
        try {
            Renter renter = renterDao.getRenterById(id);
            if (renter == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Renter not found");
            }
            return renter;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching renter", e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Renter createRenter(@Valid @RequestBody Renter renter){
        try {
            return renterDao.createRenter(renter);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating renter", e);
        }
        //return false;
    }


    //Just added need api endpoints for this
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Renter updateRenter(@PathVariable int id, @Valid @RequestBody Renter updatedRenter) {
        updatedRenter.setRenter_id(id);
        Renter existingRenter = renterDao.getRenterById(id);
        if (existingRenter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Renter not found with ID: " + id);
        }
        try {
            renterDao.updateRenter(updatedRenter);
            return updatedRenter;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating renter", e);
        }
    }



    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRenter(@PathVariable int id) {
        Renter existingRenter = renterDao.getRenterById(id);
        if (existingRenter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Renter not found with ID: " + id);
        }
        try {
            renterDao.deleteRenter(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting renter", e);
        }
    }
}
