package com.midwesttape.project.challengeapplication.service.impl;

import com.midwesttape.project.challengeapplication.mapper.UserRowMapper;
import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcTemplate template;

    @Override
    public User getUser(final Long userId) {
        try {

            return template.queryForObject(
                    "select "
                    + "id, "
                    + "firstName, "
                    + "lastName, "
                    + "username, "
                    + "password,"
                    + "addressId,"
                    + "from User "
                    + "where id = ?",
                    new UserRowMapper(),
                    userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(User user) {
        try {
            int rowsUpdated = template.update("update User set "
                    + "firstName = ?,"
                    + "lastName = ?,"
                    + "username = ?,"
                    + "password = ?,"
                    + "addressId = ? "
                    + "where id = ? ",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getAddress().getId(),
                    user.getId());
            if (rowsUpdated != 1) {
                throw new IllegalStateException("Didn't update row");
            }
            log.debug("saved user {}", user);
        } catch (DataAccessException e) {
            log.warn("unable to access data", e);
        }
    }
}
