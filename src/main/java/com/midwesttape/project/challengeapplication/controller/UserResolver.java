package com.midwesttape.project.challengeapplication.controller;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@GraphQLApi
public class UserResolver {

  private final UserService userService;

  @Autowired
  public UserResolver(UserService userService) {
    this.userService = userService;
  }

  @GraphQLQuery(name = "getUser")
  public User getUser(Long userId) {
    try {
      return userService.getUser(userId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @GraphQLMutation(name = "updateUser")
  public User updateUser(@GraphQLArgument(name = "UserInput") User user) {
    return userService.updateUser(user);
  }
}
