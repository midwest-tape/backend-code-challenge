package com.midwesttape.project.challengeapplication.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
  @Column(name = "address1", table = "address")
  private String address1;

  @Column(name = "address2", table = "address")
  private String address2;

  @Column(name = "city", table = "address")
  private String city;

  @Column(name = "state", table = "address")
  private String state;

  @Column(name = "postal", table = "address")
  private String postal;
}
