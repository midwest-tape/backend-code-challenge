package com.midwesttape.project.challengeapplication.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address {
    private Long id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postal;
    
}
