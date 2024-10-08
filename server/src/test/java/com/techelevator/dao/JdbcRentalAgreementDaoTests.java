package com.techelevator.dao;

import com.techelevator.model.RentalAgreement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JdbcRentalAgreementDaoTests extends BaseDaoTests{
    private static final RentalAgreement RENTAL_AGREEMENT_1 = new RentalAgreement(1, 1, 1, Date.valueOf("2024-08-01"), Date.valueOf("2025-07-31") , 1500.00, 2000.00,"Tenant agrees to pay rent on the 1st of each month." );

    private static final RentalAgreement RENTAL_AGREEMENT_9 = new RentalAgreement(9, 1, 9, Date.valueOf("2024-09-01"), Date.valueOf("2025-08-31") , 1800.00, 2500.00,"Tenant agrees to maintain the property in good condition." );



    private JdbcRentalAgreementDao rentalAgreementDao;

    @Before
    public void setUp() throws Exception {
        rentalAgreementDao = new JdbcRentalAgreementDao(dataSource);
    }


    @Test
    public void getAllRentalAgreements() {
        List<RentalAgreement> agreements = rentalAgreementDao.getAllRentalAgreements();
        assertFalse(agreements.isEmpty());
    }

    @Test
    public void getRentalAgreementById() {
        RentalAgreement agreement = rentalAgreementDao.getRentalAgreementById(1);
        assertNotNull(agreement);
        assertEquals(RENTAL_AGREEMENT_1, agreement);
    }



    @Test
    public void createRentalAgreement() {
        RentalAgreement newAgreement = new RentalAgreement(10,1, 1, Date.valueOf("2024-09-01"), Date.valueOf("2024-10-10"), 1500.00, 2000.00, "Tenant agrees to pay rent on the 1st of each month.");

        RentalAgreement createdAgreement = rentalAgreementDao.createRentalAgreement(newAgreement);
        if(createdAgreement != null){
            assertEquals(newAgreement, rentalAgreementDao.getRentalAgreementById(10));
        }
    }


    @Test
    public void updateRentalAgreement() {
        RentalAgreement agreementToUpdate = rentalAgreementDao.getRentalAgreementById(9);
        agreementToUpdate.setAgreement("Agreement updated");
        agreementToUpdate.setDeposit_amount(1600.00);
        agreementToUpdate.setMonthly_rent(1200.00);
        agreementToUpdate.setProperty_id(8);
        agreementToUpdate.setRenter_id(2);
        agreementToUpdate.setStart_date(Date.valueOf("2024-09-10"));
        agreementToUpdate.setEnd_date(Date.valueOf("2025-09-30"));
        boolean updatedAgreement = rentalAgreementDao.updateRentalAgreement(agreementToUpdate);
        if(updatedAgreement){
            assertEquals(agreementToUpdate, rentalAgreementDao.getRentalAgreementById(9));
        } else {
            Assert.fail("Update failed");
        }
    }


    @Test
    public void deleteRentalAgreement() {
        boolean rowsDeleted = rentalAgreementDao.deleteRentalAgreement(1);
        if(rowsDeleted){
            try{
                RentalAgreement deletedAgreement = rentalAgreementDao.getRentalAgreementById(1);
            } catch( EmptyResultDataAccessException e){
                Assert.assertTrue(true);

            }
        } else {
            Assert.fail("Delete failed");
        }
    }



    @Test
    public void getRentalAgreementsByRenterId() {
        List<RentalAgreement> agreements = rentalAgreementDao.getRentalAgreementsByRenterId(1);
        Assert.assertEquals(2, agreements.size());
        assertEquals(RENTAL_AGREEMENT_1, agreements.get(0));
        assertEquals(RENTAL_AGREEMENT_9, agreements.get(1));
    }

    @Test
    public void getRentalAgreementsByPropertyId() {
        List<RentalAgreement> agreements = rentalAgreementDao.getRentalAgreementsByPropertyId(1);
        Assert.assertEquals(1, agreements.size());
        assertEquals(RENTAL_AGREEMENT_1, agreements.get(0));


    }


    private void assertEquals(RentalAgreement expected, RentalAgreement actual){
        Assert.assertEquals(expected.getRental_agreement_id(),actual.getRental_agreement_id());
        Assert.assertEquals(expected.getRenter_id(),actual.getRenter_id());
        Assert.assertEquals(expected.getProperty_id(),actual.getProperty_id());
        Assert.assertEquals(expected.getStart_date(),actual.getStart_date());
        Assert.assertEquals(expected.getEnd_date(),actual.getEnd_date());
        Assert.assertEquals(expected.getMonthly_rent(),actual.getMonthly_rent(), .05);
        Assert.assertEquals(expected.getDeposit_amount(),actual.getDeposit_amount(), .05);
        Assert.assertEquals(expected.getAgreement(),actual.getAgreement());

    }

}
