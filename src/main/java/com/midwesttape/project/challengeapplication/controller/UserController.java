package com.midwesttape.project.challengeapplication.controller;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(@Qualifier("userServiceImpl") UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/v1/users/{userId}")
  public User user(@PathVariable final Long userId) {
    return userService.getUser(userId);
  }

  @PostMapping("/v1/users")
  public User updateUser(@RequestBody final User user) {
    if (user == null || user.getId() == null) {
      throw new IllegalArgumentException();
    }
    return userService.updateUser(user);
  }
}
