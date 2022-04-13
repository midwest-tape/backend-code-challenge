package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.User;

public interface UserService {

    User getUser(Long userId);

    void update(User user);
}
