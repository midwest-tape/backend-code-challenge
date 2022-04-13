package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    private static final Long ADDRESS_ID = 1234L;

    @Mock
    private JdbcTemplate template;

    private AddressServiceImpl addressService;

    @BeforeEach
    public void beforeEach() {
        addressService = new AddressServiceImpl(template);
    }

    @Test
    public void should_get_Address() {

        final Address address = new Address();

        when(template.queryForObject(anyString(), isA(BeanPropertyRowMapper.class), eq(ADDRESS_ID))).thenReturn(address);

        final Address resultAddress = addressService.getAddress(ADDRESS_ID);

        assertEquals(address, resultAddress);


    }

}