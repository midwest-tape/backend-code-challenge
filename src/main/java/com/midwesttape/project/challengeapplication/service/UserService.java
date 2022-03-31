package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.mapper.UserRowMapper;
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
                    "user.password, " +
                    "addr.id, " +
                    "addr.address1, " +
                    "addr.address2, " +
                    "addr.city, " +
                    "addr.state, " +
                    "addr.postal"+
                    " from User user join address addr  on" +
                    " user.addressId = addr.id" +
                    " where user.id = ?",
                new UserRowMapper(),
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public String updateUser(User user) {
        try {
            int userUpdate= template.update(
                "update user user set " +
                    "user.firstName =?, " +
                    "user.lastName= ?, " +
                    "user.username= ? " +
                    " where id =?",
                    new Object[]{user.getFirstName(),user.getLastName(),user.getUserName(),user.getId()});

            Address address = user.getAddress();

            int addressUpdate= template.update("update address address set " +
                    "address.address1 =?, " +
                    "address.address2 = ?, " +
                    "address.city = ?, " +
                    "address.state = ?, " +
                    "address.postal= ? "+
                    " where id = (select addressId from user where id= ?)",
            new Object[]{address.getAddress1(),address.getAddress2(),
                address.getCity(),address.getState(),
                address.getPostal(),user.getId()});


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
