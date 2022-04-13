package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.facade.UserFacade;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/v1/users/{userId}")
    public User getUser(@PathVariable final Long userId) {
        User user = userFacade.getUserWithAddress(userId);
        return user;
    }
    
    @PutMapping("/v1/users/{userId}")
    public User updateUser(@PathVariable final Long userId, @RequestBody User user) {
        User updatedUser = userFacade.updateUser(user);
        return updatedUser;
    }
}
