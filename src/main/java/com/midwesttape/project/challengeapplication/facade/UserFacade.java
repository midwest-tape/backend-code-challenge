package com.midwesttape.project.challengeapplication.facade;

import com.midwesttape.project.challengeapplication.model.User;

public interface UserFacade {

    User getUserWithAddress(Long userId);

    User updateUser(User user);
}
