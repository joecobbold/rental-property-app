package com.techelevator.model;

import java.util.Date;

public class RentalAgreement {
    private int rental_agreement_id;
    private int renter_id;
    private int property_id;
    private Date start_date;
    private Date end_date;
    private double monthly_rent;
    private double deposit_amount;

    public void setRental_agreement_id(int rental_agreement_id) {
        this.rental_agreement_id = rental_agreement_id;
    }

    public void setRenter_id(int renter_id) {
        this.renter_id = renter_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setMonthly_rent(double monthly_rent) {
        this.monthly_rent = monthly_rent;
    }

    public void setDeposit_amount(double deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    private String agreement;



    public RentalAgreement(int rentalAgreementId, int renterId, int propertyId, Date startDate, Date endDate, double monthlyRent, double depositAmount, String agreement) {
        rental_agreement_id = rentalAgreementId;
        renter_id = renterId;
        property_id = propertyId;
        start_date = startDate;
        end_date = endDate;
        monthly_rent = monthlyRent;
        deposit_amount = depositAmount;
        this.agreement = agreement;
    }

    public int getRental_agreement_id() {
        return rental_agreement_id;
    }

    public int getRenter_id() {
        return renter_id;
    }

    public int getProperty_id() {
        return property_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public double getMonthly_rent() {
        return monthly_rent;
    }

    public double getDeposit_amount() {
        return deposit_amount;
    }

    public String getAgreement() {
        return agreement;
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "renter_id=" + renter_id +
                ", property_id=" + property_id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", monthly_rent=" + monthly_rent +
                ", deposit_amount=" + deposit_amount +
                ", agreement='" + agreement + '\'' +
                '}';
    }
}
