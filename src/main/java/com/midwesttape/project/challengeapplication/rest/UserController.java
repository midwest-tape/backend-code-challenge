package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @GetMapping("/v1/users/{userId}")
    public User user(@PathVariable final Long userId) {
    	return userService.user(userId);
    }
    
    @PutMapping(path = "/v1/users/{userId}",
    		consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody User user) {
    	user.setId(userId);
    	Integer count = userService.save(user);
    	if(count==0) {
    		return new ResponseEntity<>(user, HttpStatus.NOT_MODIFIED);
    	} else {
    		return new ResponseEntity<>(user, HttpStatus.CREATED);
    	}
    }
}
