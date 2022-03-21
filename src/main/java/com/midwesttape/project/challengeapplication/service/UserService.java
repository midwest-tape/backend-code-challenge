package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate template;

    public User getUser(final Long userId) {
        try {
            User user = template.queryForObject(
                "select * from Users where id = ?",
                new BeanPropertyRowMapper<>(User.class),
                userId
            );
            Integer addressId = user.getAddressId();
            if (addressId == null) {
               return user;
            }
            Address address = template.queryForObject(
                "select * from Address where id = ?",
                new BeanPropertyRowMapper<>(Address.class),
                addressId
            );
           user.setAddress(address);
           return user;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }


    public void updateUser(User user) {

        String updateUserSql = "update Users set firstName = ?, lastName = ?, username = ?, password = ? where id = ?";
        template.update(updateUserSql,
            user.getFirstName(),
            user.getLastName(),
            user.getUsername(),
            user.getPassword(),
            user.getId());
    }
}