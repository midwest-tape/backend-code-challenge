package com.midwesttape.project.challengeapplication.facade.impl;

import com.midwesttape.project.challengeapplication.facade.UserFacade;
import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.impl.AddressServiceImpl;
import com.midwesttape.project.challengeapplication.service.impl.UserServiceImpl;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@GraphQLApi
public class UserFacadeImpl implements UserFacade {

    private final UserServiceImpl userService;
    private final AddressServiceImpl addressService;

    @Override
    @GraphQLQuery(name = "getUserWithAddress")
    public User getUserWithAddress(@GraphQLArgument(name = "userId") Long userId) {
        User user = userService.getUser(userId);
        Address address = addressService.getAddress(user.getAddress().getId());
        user.setAddress(address);
        return user;
    }

    @Override
    @GraphQLMutation(name = "updateUser")
    public User updateUser(@GraphQLArgument(name = "modifiedUser") User user) {
        addressService.update(user.getAddress());
        Address savedAddress = addressService.getAddress(user.getAddress().getId());
        user.setAddress(savedAddress);
        userService.update(user);
        User savedUser = userService.getUser(user.getId());    
        savedUser.setAddress(savedAddress);    
        return savedUser;
    }
}
