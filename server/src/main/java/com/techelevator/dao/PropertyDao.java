package com.techelevator.dao;

import com.techelevator.model.Property;

import java.util.List;

public interface PropertyDao {
    List<Property> getAllProperties();

    Property getPropertyById(int id);

    Property createProperty(Property newProperty);

    boolean updateProperty(Property updatedProperty);

    boolean deleteProperty(int id);
}
