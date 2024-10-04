package com.techelevator.model;

public class Renter {

    private int renter_id;

    public int getRenter_id() {
        return renter_id;
    }

    public void setRenter_id(int renter_id) {
        this.renter_id = renter_id;
    }

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String profile_details;


    public Renter(int renter_id, String firstName, String lastName, String email, String phone, String profileDetails) {
        this.renter_id = renter_id;
        first_name = firstName;
        last_name = lastName;
        this.email = email;
        this.phone = phone;
        profile_details = profileDetails;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfile_details() {
        return profile_details;
    }


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProfile_details(String profile_details) {
        this.profile_details = profile_details;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", profile_details='" + profile_details + '\'' +
                '}';
    }
}
