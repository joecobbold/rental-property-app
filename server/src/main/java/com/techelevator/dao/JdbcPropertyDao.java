package com.techelevator.dao;

import com.techelevator.model.Property;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class JdbcPropertyDao implements PropertyDao{

    private final JdbcTemplate template;


    public JdbcPropertyDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Property> mapper = new RowMapper<Property>() {
        @Override
        public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
            int propertyId = rs.getInt("property_id");
            String address = rs.getString("address");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String zipCode = rs.getString("zip_code");
            double rentPrice = rs.getDouble("rent_price");
            int bedrooms = rs.getInt("bedrooms");
            int bathrooms = rs.getInt("bathrooms");
            int squareFeet = rs.getInt("square_feet");
            boolean isAvailable = rs.getBoolean("available");
            boolean hasBasement = rs.getBoolean("basement");
            String description = rs.getString("description");
            String imageUrl = rs.getString("imageUrl");
            Property property = new Property(propertyId, address, city, state, zipCode, rentPrice, bedrooms, bathrooms, squareFeet, isAvailable, hasBasement, description, imageUrl);
            return property;
        }
    };

    @Override
    public List<Property> getAllProperties() {
        return template.query("SELECT * FROM property", mapper);
    }

    @Override
    public Property getPropertyById(int id) {
        return template.queryForObject( "SELECT * FROM property WHERE property_id = ?", mapper, id);
    }

    @Override
    public Property createProperty(Property newProperty) {
        String sql = "INSERT INTO property (address, city, state, zip_code, rent_price, bedrooms, bathrooms, square_feet, available, basement, description, imageUrl) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning property_id";
        int propertyId = template.queryForObject(sql, int.class, newProperty.getAddress(), newProperty.getCity(), newProperty.getState(),
                newProperty.getZipCode(), newProperty.getRentPrice(), newProperty.getBedrooms(),
                newProperty.getBathrooms(), newProperty.getSquareFeet(), newProperty.isAvailable(), newProperty.isBasement(),
                newProperty.getDescription(), newProperty.getImageUrl());
        return getPropertyById(propertyId);
    }

    @Override
    public boolean updateProperty(Property property) {
        String sql = "UPDATE property SET address = ?, city = ?, state = ?, zip_code = ?, rent_price = ?, bedrooms = ?, bathrooms = ?, square_feet = ?, available = ?, basement = ?, description = ?, imageUrl = ? " +
                "WHERE property_id = ?";
        int rowsAffected = template.update(sql, property.getAddress(), property.getCity(), property.getState(),
                property.getZipCode(), property.getRentPrice(), property.getBedrooms(),
                property.getBathrooms(), property.getSquareFeet(), property.isAvailable(), property.isBasement(),
                property.getDescription(), property.getImageUrl(), property.getPropertyId());
        return rowsAffected > 0;
    }


    @Override
    public boolean deleteProperty(int id) {
        int rowsAffected = template.update("DELETE FROM property WHERE property_id = ?", id);
        return rowsAffected > 0;
    }

//    @Override
//    public List<Property> getAllProperties() {
//        List<Property> properties = new ArrayList<>();
//        String sql = "SELECT * FROM property";
//
//        SqlRowSet rs = template.queryForRowSet(sql);
//
//        while(rs.next()){
//            Property property = mapRowToProperty(rs);
//            properties.add(property);
//        }
//        return properties;
//    }
//
//    private static Property mapRowToProperty(SqlRowSet rs) {
//        String address = rs.getString("address");
//        String city = rs.getString("city");
//        String state = rs.getString("state");
//        String zipCode = rs.getString("zip_code");
//        double rentPrice = rs.getDouble("rent_price");
//        int bedrooms = rs.getInt("bedrooms");
//        int bathrooms = rs.getInt("bathrooms");
//        int squareFeet = rs.getInt("square_feet");
//        boolean isAvailable = rs.getBoolean("available");
//        boolean hasBasement = rs.getBoolean("basement");
//        String description = rs.getString("description");
//        Property property = new Property(address, city, state, zipCode, rentPrice, bedrooms, bathrooms, squareFeet, isAvailable, hasBasement, description);
//        return property;
//    }
}
