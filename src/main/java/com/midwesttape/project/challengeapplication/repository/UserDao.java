package com.midwesttape.project.challengeapplication.repository;

import com.midwesttape.project.challengeapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
  User findUserById(Long userId);
}
