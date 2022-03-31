package com.midwesttape.project.challengeapplication.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Getter
@Setter
public class Address {

    private Long id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postal;
}
