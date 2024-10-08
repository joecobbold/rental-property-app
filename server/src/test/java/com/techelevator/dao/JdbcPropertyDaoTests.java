package com.techelevator.dao;

import com.techelevator.model.Property;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JdbcPropertyDaoTests extends BaseDaoTests{

    private static final Property PROPERTY_1 = new Property(1, "123 Main St", "Anytown", "CA", "12345", 1500.00, 3, 2, 1500, true, false, "Cozy family home with backyard");


    private static final Property PROPERTY_2 = new Property(2,  "456 Oak Ave", "Sometown", "NY", "67890", 1800.00, 4, 3, 2000, true, true, "Spacious house with basement");



    private JdbcPropertyDao propertyDao;



    @Before
    public void setUp() throws Exception {
        propertyDao = new JdbcPropertyDao(dataSource);

    }

    @Test
    public void getAllProperties() {
        List<Property> properties = propertyDao.getAllProperties();
        assertFalse(properties.isEmpty());

    }

    @Test
    public void getPropertyById() {
        Property property = propertyDao.getPropertyById(1);
        assertNotNull(property);
        assertEquals(PROPERTY_1, property);
    }

    @Test
    public void createProperty() {
        Property newProperty = new Property(11,"123 Main St", "Anytown", "CA", "12345", 1500.00, 3, 2, 1500, true, false, "Cozy family home with backyard");

        Property createdAgreement = propertyDao.createProperty(newProperty);
        if(createdAgreement != null){
            assertEquals(newProperty, propertyDao.getPropertyById(11));
        }

    }




    @Test
    public void updateProperty() {
        Property propertyToUpdate = propertyDao.getPropertyById(2);

        propertyToUpdate.setAddress("123 Update");
        propertyToUpdate.setCity("Columbus");
        propertyToUpdate.setState("Ohio");
        propertyToUpdate.setZipCode("43081");
        propertyToUpdate.setRentPrice(1100.00);
        propertyToUpdate.setBedrooms(6);
        propertyToUpdate.setBathrooms(4);
        propertyToUpdate.setSquareFeet(4000);
        propertyToUpdate.setAvailable(false);
        propertyToUpdate.setBasement(false);
        propertyToUpdate.setDescription("This is the updated property");

        boolean updatedAgreement = propertyDao.updateProperty(propertyToUpdate);
        if(updatedAgreement){
            assertEquals(propertyToUpdate, propertyDao.getPropertyById(2));
        } else {
            Assert.fail("Update failed");
        }
    }




    @Test
    public void deleteProperty() {
        boolean rowsDeleted = propertyDao.deleteProperty(1);
        if(rowsDeleted){
            try{
                Property deletedProperty = propertyDao.getPropertyById(1);
            } catch( EmptyResultDataAccessException e){
                Assert.assertTrue(true);

            }
        } else {
            Assert.fail("Delete failed");
        }
    }



    private void assertEquals(Property expected, Property actual){
        Assert.assertEquals(expected.getPropertyId(),actual.getPropertyId());
        Assert.assertEquals(expected.getAddress(),actual.getAddress());
        Assert.assertEquals(expected.getBathrooms(),actual.getBathrooms(), 0.0);
        Assert.assertEquals(expected.getBedrooms(),actual.getBedrooms(), 0.0);
        Assert.assertEquals(expected.getCity(),actual.getCity());
        Assert.assertEquals(expected.getRentPrice(),actual.getRentPrice(), .05);
        Assert.assertEquals(expected.getSquareFeet(),actual.getSquareFeet(), .05);
        Assert.assertEquals(expected.getZipCode(),actual.getZipCode());
        Assert.assertEquals(expected.getState(),actual.getState());
        Assert.assertEquals(expected.getDescription(),actual.getDescription());
        Assert.assertEquals(expected.isBasement(),actual.isBasement());
        Assert.assertEquals(expected.isAvailable(),actual.isAvailable());

    }
}
