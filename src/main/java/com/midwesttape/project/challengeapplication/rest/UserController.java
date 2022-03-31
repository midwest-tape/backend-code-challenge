package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/v1/users/{userId}")
    public User getUser(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    @PostMapping("/v1/updateUserInfo")
    public String createUser(@RequestBody final User user) {
        return userService.updateUser(user);
    }
}
