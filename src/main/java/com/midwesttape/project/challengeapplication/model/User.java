package com.midwesttape.project.challengeapplication.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SecondaryTable(name = "address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "first_Name")
  private String firstName;

  @Column(name = "last_Name")
  private String lastName;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Embedded private Address address;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
