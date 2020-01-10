package com.zero.springsecurity.authentication.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private String password;
  private String fullName;

  public UserEntity() {
  }

  public int getId() {
    return id;
  }

  public UserEntity setId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public UserEntity setFullName(String fullname) {
    this.fullName = fullname;
    return this;
  }
}