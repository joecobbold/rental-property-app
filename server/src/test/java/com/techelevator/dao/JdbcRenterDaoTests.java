package com.techelevator.dao;

import com.techelevator.model.Renter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JdbcRenterDaoTests extends BaseDaoTests {

    private static final Renter RENTER_1 = new Renter(1, "John", "Doe", "john.doe@example.com", "123-456-7890", "Likes to keep the house clean");

    private JdbcRenterDao renterDao;


    @Before
    public void setUp() throws Exception {
        renterDao = new JdbcRenterDao(dataSource);
    }


    @Test
    public void getAllRenters() {
        List<Renter> renters = renterDao.getAllRenters();
        assertFalse(renters.isEmpty());

    }


    @Test
    public void getRenterById() {
        Renter renter = renterDao.getRenterById(1);
        assertNotNull(renter);
        assertEquals(RENTER_1, renter);
    }


    @Test
    public void createRenter() {
        Renter newRenter = new Renter(9, "Joe", "Cobbold", "joe.cobbold@example.com", "111-222-3333", "Joe created this new Renter");

        boolean createdRenter = renterDao.createRenter(newRenter);
        if(createdRenter){
            assertEquals(newRenter, renterDao.getRenterById(9));
        }
    }


    @Test
    public void updateRenter() {
    }


    @Test
    public void deleteRenter() {
        boolean rowsDeleted = renterDao.deleteRenter(1);
        if(rowsDeleted){
            try{
                Renter deletedRenter = renterDao.getRenterById(1);
            } catch( EmptyResultDataAccessException e){
                Assert.assertTrue(true);

            }
        } else {
            Assert.fail("Delete failed");
        }
    }


    private void assertEquals(Renter expected, Renter actual){
        Assert.assertEquals(expected.getRenter_id(), actual.getRenter_id());
        Assert.assertEquals(expected.getFirst_name(),actual.getFirst_name());
        Assert.assertEquals(expected.getLast_name(),actual.getLast_name());
        Assert.assertEquals(expected.getEmail(),actual.getEmail());
        Assert.assertEquals(expected.getPhone(),actual.getPhone());
        Assert.assertEquals(expected.getProfile_details(),actual.getProfile_details());

    }
}