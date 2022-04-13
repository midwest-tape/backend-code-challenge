package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;

public interface AddressService {

    Address getAddress(final Long addressId);

    void update(Address address);

}
