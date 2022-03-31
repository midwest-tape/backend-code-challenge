package com.midwesttape.project.challengeapplication.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.model.Address;

public class UserRowMapper  implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getLong("id"));

        Address address  = new Address();
        address.setAddress1(rs.getString("address1"));
        address.setAddress2(rs.getString("address2"));
        address.setId(rs.getLong("id"));
        address.setPostal(rs.getString("postal"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        user.setAddress(address);

        return user;

    }
}
