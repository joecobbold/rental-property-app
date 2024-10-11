package com.techelevator.controller;

import com.techelevator.dao.PropertyDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Property;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path="/property")
//@PreAuthorize("isAuthenticated()")
public class PropertiesController {


    private final PropertyDao propertyDao;



    public PropertiesController(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }




    @GetMapping
    public List<Property> getAllProperties(){
        try {
            return propertyDao.getAllProperties();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching properties", e);
        }
    }




    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path="/{id}")
    public Property getPropertyById(@PathVariable int id){
        try {
            Property property = propertyDao.getPropertyById(id);
            if (property == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
            }
            return property;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching property", e);
        }
    }




    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Property createProperty(@Valid @RequestBody Property newProperty) {
        try {
            return propertyDao.createProperty(newProperty);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating property", e);
        }
    }




    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Property updateProperty(@Valid @PathVariable int id, @RequestBody Property property) {
        property.setPropertyId(id);
        if (property.getPropertyId() != id) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Property id must match id in path");
        }
        Property existingProperty = propertyDao.getPropertyById(id);
        if (existingProperty == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
        }
        try {
            boolean updatedProperty = propertyDao.updateProperty(property);
            if (!updatedProperty) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating property");
            }
            return property;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating property", e);
        }
    }




    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    //@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteProperty(@PathVariable int id) {
        try {
            boolean isDeleted = propertyDao.deleteProperty(id);
            if (!isDeleted) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting property", e);
        }
    }
}
