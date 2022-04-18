package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User getUser(final Long userId) {
    try {
      return userDao.findUserById(userId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Override
  public User updateUser(User user) {
    User oldUser = userDao.findUserById(user.getId());
    if (user.getFirstName() != null) oldUser.setFirstName(user.getFirstName());
    if (user.getLastName() != null) oldUser.setLastName(user.getLastName());
    if (user.getUsername() != null) oldUser.setUsername(user.getUsername());
    if (user.getPassword() != null) oldUser.setPassword(user.getPassword());

    Address oldAddress = oldUser.getAddress();
    Address newAddress = user.getAddress();
    if(newAddress!=null){
    if (newAddress.getAddress1() != null) oldAddress.setAddress1(newAddress.getAddress1());
    if (newAddress.getAddress2() != null) oldAddress.setAddress2(newAddress.getAddress2());
    if (newAddress.getCity() != null) oldAddress.setCity(newAddress.getCity());
    if (newAddress.getState() != null) oldAddress.setState(newAddress.getState());
    if (newAddress.getPostal() != null) oldAddress.setPostal(newAddress.getPostal());}

    oldUser.setAddress(oldAddress);
    userDao.save(oldUser);
    return userDao.findUserById(user.getId());
  }
}
