package com.techelevator.model;

public class Property {
    private int propertyId;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private double rentPrice;

    private double bedrooms;
    private double bathrooms;
    private double squareFeet;
    private boolean available;
    private boolean basement;
    private String description;
    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setSquareFeet(double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public double getSquareFeet() {
        return squareFeet;
    }


    public int getPropertyId() {
        return propertyId;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isBasement() {
        return basement;
    }

    public Property(int propertyId, String address, String city, String state, String zipCode, double rentPrice, double bedrooms, double bathrooms, double squareFeet, boolean isAvailable, boolean hasBasement, String description, String imageUrl) {
        this.propertyId = propertyId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.rentPrice = rentPrice;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFeet = squareFeet;
        available = isAvailable;
        basement = hasBasement;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Property{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip_code='" + zipCode + '\'' +
                ", rent_price=" + rentPrice +
                ", description='" + description + '\'' +
                '}';
    }
}
