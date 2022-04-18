package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.User;

public interface UserService {
  User getUser(Long userId);

  User updateUser(User user);
}
