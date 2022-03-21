package com.midwesttape.project.challengeapplication.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Integer id;
    private Integer addressId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Address address;
}
