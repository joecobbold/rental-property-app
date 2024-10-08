package com.techelevator.dao;

import com.techelevator.model.RentalAgreement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



@Component
public class JdbcRentalAgreementDao implements RentalAgreementDao{

    private final JdbcTemplate template;


    public JdbcRentalAgreementDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    private RowMapper<RentalAgreement> mapper = new RowMapper<RentalAgreement>() {
        //Anonymous class
        @Override
        public RentalAgreement mapRow(ResultSet rs, int rowNum) throws SQLException {
            int rentalAgreementId = rs.getInt("rental_agreement_id");
            int renterId = rs.getInt("renter_id");
            int propertyId = rs.getInt("property_id");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            double monthlyRent = rs.getDouble("monthly_rent");
            double depositAmount = rs.getDouble("deposit_amount");
            String agreement = rs.getString("agreement");
            RentalAgreement rentalAgreement = new RentalAgreement(rentalAgreementId, renterId, propertyId, startDate, endDate, monthlyRent, depositAmount, agreement);
            return rentalAgreement;
        }
    };

    @Override
    public List<RentalAgreement> getAllRentalAgreements() {
        return template.query("SELECT * FROM rental_agreement", mapper);
    }

    @Override
    public RentalAgreement getRentalAgreementById(int id) {
        return template.queryForObject( "SELECT * FROM rental_agreement WHERE rental_agreement_id = ?", mapper, id);
    }

    @Override
    public RentalAgreement createRentalAgreement(RentalAgreement newRentalAgreement) {
        String sql = "INSERT INTO rental_agreement (renter_id, property_id, start_date, end_date, monthly_rent, deposit_amount, agreement) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) returning rental_agreement_id";
        int rentalAgreementId = template.queryForObject(sql, int.class, newRentalAgreement.getRenter_id(), newRentalAgreement.getProperty_id(),
                newRentalAgreement.getStart_date(), newRentalAgreement.getEnd_date(), newRentalAgreement.getMonthly_rent(),
                newRentalAgreement.getDeposit_amount(), newRentalAgreement.getAgreement());
        return getRentalAgreementById(rentalAgreementId);
    }

    @Override
    public boolean updateRentalAgreement(RentalAgreement updatedRentalAgreement) {
        String sql = "UPDATE rental_agreement SET renter_id = ?, property_id = ?, start_date = ?, end_date = ?, " +
                "monthly_rent = ?, deposit_amount = ?, agreement = ? WHERE \n" +
                "rental_agreement_id = ?";
        int rowsAffected = template.update(sql, updatedRentalAgreement.getRenter_id(), updatedRentalAgreement.getProperty_id(),
                updatedRentalAgreement.getStart_date(), updatedRentalAgreement.getEnd_date(), updatedRentalAgreement.getMonthly_rent(),
                updatedRentalAgreement.getDeposit_amount(), updatedRentalAgreement.getAgreement(), updatedRentalAgreement.getRental_agreement_id());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteRentalAgreement(int id) {
        int rowsAffected = template.update("DELETE FROM rental_agreement WHERE rental_agreement_id = ?", id);
        return rowsAffected > 0;
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByRenterId(int renterId) {
        String sql = "SELECT * FROM rental_agreement WHERE renter_id = ?";
        return template.query(sql, mapper, renterId);
    }

    @Override
    public List<RentalAgreement> getRentalAgreementsByPropertyId(int propertyId) {
        String sql = "SELECT * FROM rental_agreement WHERE property_id = ?";
        return template.query(sql, mapper, propertyId);
    }

//    @Override
//    public List<RentalAgreement> getAllRentalAgreements() {
//        List<RentalAgreement> rentalAgreements = new ArrayList<>();
//
//        String sql = "SELECT * FROM rental_agreement";
//
//        SqlRowSet rs = template.queryForRowSet(sql);
//
//        while(rs.next()){
//            RentalAgreement rentalAgreement = mapRowToRentalAgreement(rs);
//            rentalAgreements.add(rentalAgreement);
//        }
//        return rentalAgreements;
//    }
//
//
//    private static RentalAgreement mapRowToRentalAgreement(SqlRowSet rs) {
//        int renterId = rs.getInt("renter_id");
//        int propertyId = rs.getInt("property_id");
//        Date startDate = rs.getDate("start_date");
//        Date endDate = rs.getDate("end_date");
//        double monthlyRent = rs.getDouble("monthly_rent");
//        double depositAmount = rs.getDouble("deposit_amount");
//        String agreement = rs.getString("agreement");
//        RentalAgreement rentalAgreement = new RentalAgreement(renterId, propertyId, startDate, endDate, monthlyRent, depositAmount, agreement);
//        return rentalAgreement;
//    }
}
