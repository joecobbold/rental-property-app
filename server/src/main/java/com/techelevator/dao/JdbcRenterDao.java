package com.techelevator.dao;

import com.techelevator.model.Renter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcRenterDao implements RenterDao{

    private final JdbcTemplate template;

    public JdbcRenterDao(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }



    private RowMapper<Renter> mapper = new RowMapper<Renter>() {
        @Override
        public Renter mapRow(ResultSet rs, int rowNum) throws SQLException {
            int renterId = rs.getInt("renter_id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String profile_details = rs.getString("profile_details");
            Renter renter = new Renter(renterId, first_name, last_name, email, phone, profile_details);
            return renter;
        }
    };



    @Override
    public List<Renter> getAllRenters() {
        return template.query("SELECT * FROM renter", mapper);
    }

    @Override
    public Renter getRenterById(int id) {
        return template.queryForObject( "SELECT * FROM renter WHERE renter_id = ?", mapper, id);
    }

    @Override
    public Renter createRenter(Renter renter) {
        String sql = "INSERT INTO renter (first_name, last_name, email, phone, profile_details) " +
                "VALUES (?, ?, ?, ?, ?) returning renter_id";
        int renterId = template.queryForObject(sql, int.class, renter.getFirst_name(), renter.getLast_name(), renter.getEmail(),
                renter.getPhone(), renter.getProfile_details());
        return getRenterById(renterId);
    }

    @Override
    public boolean updateRenter(Renter updatedRenter) {
        String sql = "UPDATE renter SET first_name = ?, last_name = ?, email = ?, phone = ?, profile_details = ? " +
                "WHERE renter_id = ?";
        int rowsAffected = template.update(sql, updatedRenter.getFirst_name(), updatedRenter.getLast_name(), updatedRenter.getEmail(),
                updatedRenter.getPhone(), updatedRenter.getProfile_details(), updatedRenter.getRenter_id());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteRenter(int id) {
        String deleteAgreementsSql = "DELETE FROM rental_agreement WHERE renter_id = ?";
        template.update(deleteAgreementsSql, id);

        String deleteRenterSql = "DELETE FROM renter WHERE renter_id = ?";
        int rowsAffected = template.update(deleteRenterSql, id);
        return rowsAffected > 0;
    }



//    @Override
//    public List<Renter> getAllRenters() {
//        List<Renter> renters = new ArrayList<>();
//
//        String sql = "SELECT * FROM renter";
//
//        SqlRowSet rs = template.queryForRowSet(sql);
//
//        while(rs.next()){
//            Renter renter = mapRowToRenter(rs);
//            renters.add(renter);
//        }
//        return renters;
//    }
//
//    private static Renter mapRowToRenter(SqlRowSet rs) {
//        String first_name = rs.getString("first_name");
//        String last_name = rs.getString("last_name");
//        String email = rs.getString("email");
//        String phone = rs.getString("phone");
//        String profile_details = rs.getString("profile_details");
//        Renter renter = new Renter(first_name, last_name, email, phone, profile_details);
//        return renter;
//    }
}
