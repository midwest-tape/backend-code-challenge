package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private JdbcTemplate template;

    public User user(final Long userId) {
        try {

            return template.queryForObject(
                "select " +
                    "user.id, " +
                    "user.firstName, " +
                    "user.lastName, " +
                    "user.username, " +
                    "user.password " +
                    "address.id, " +
                    "address.address1, " +
                    "address.address2, " +
                    "address.city, " +
                    "address.state, " +
                    "address.postal"+
                    "from User user join  address address   on user.addressId = address.id" +
                    "where user.id = ?",
                new BeanPropertyRowMapper<>(User.class),
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public String updateUser(User user) {
        try {
            int userUpdate= template.update(
                "update user " +
                    "user.firstName =?, " +
                    "user.lastName= ?, " +
                    "user.username= ?, " +
                    "where id =?",
                    new Object[]{user.getFirstName(),user.getLastName(),user.getUserName(),user.getId()});
            int addressUpdate= template.update("update address " +
                    "address.address1 =? " +
                    "address.address2 = ?, " +
                    "address.city = ?, " +
                    "address.state = ?, " +
                    "address.postal= ?"+
                    "where id = (select addressId from user where id= ?)",
            new Object[]{user.getAddress().getAddress1(),user.getAddress().getAddress2(),user.getAddress().getCity(),user.getAddress().getState(),user.getAddress().getPostal(),user.getId});
            if(userUpdate> 0 || addressUpdate >0){
                return  "User has been updated";
            }
            else{
                return  "User has been not update";
            }
        } catch (EmptyResultDataAccessException e) {
            return "Failed to update User information";
        }
    }

}
