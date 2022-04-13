package com.midwesttape.project.challengeapplication.mapper;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        
        Address address = new Address();
        address.setId(rs.getLong("addressId"));
        user.setAddress(address);

        return user;
    }
}
