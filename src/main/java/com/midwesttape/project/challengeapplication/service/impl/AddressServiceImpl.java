package com.midwesttape.project.challengeapplication.service.impl;

import com.midwesttape.project.challengeapplication.service.AddressService;
import com.midwesttape.project.challengeapplication.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final JdbcTemplate template;

    @Override
    public Address getAddress(final Long addressId) {
        try {

            return template.queryForObject(
                    "select "
                    + "id, "
                    + "address1, "
                    + "address2, "
                    + "city, "
                    + "state, "
                    + "postal "
                    + "from Address "
                    + "where id = ?",
                    new BeanPropertyRowMapper<>(Address.class),
                    addressId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Address address) {
        try {
            int rowsUpdated = template.update("update Address set "
                    + "address1 = ?,"
                    + "address2 = ?,"
                    + "city = ?,"
                    + "state = ?,"
                    + "postal = ? "
                    + "where id = ? ",
                    address.getAddress1(),
                    address.getAddress2(),
                    address.getCity(),
                    address.getState(),
                    address.getPostal(),
                    address.getId());

            if (rowsUpdated != 1) {
                throw new IllegalStateException("Didn't update row");
            }
            log.debug("saved address {}", address);
        } catch (DataAccessException e) {
            log.warn("unable to access data", e);
        }
    }
}
