package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users/{userId}")
    public User user(@PathVariable final Long userId) {
        User user =  userService.getUser(userId);
        return user;
    }


    @RequestMapping(value = "/v1/users", method = RequestMethod.PUT, consumes="application/json")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
