package com.midwesttape.project.challengeapplication.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Setter
@Getter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
