package com.techelevator.dao;

import com.techelevator.model.Renter;

import java.util.List;

public interface RenterDao {
    List<Renter> getAllRenters();

    Renter getRenterById(int id);

    boolean createRenter(Renter newRenter);

    boolean updateRenter(Renter updatedRenter);

    boolean deleteRenter(int id);
}
