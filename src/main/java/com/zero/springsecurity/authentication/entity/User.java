package com.zero.springsecurity.authentication.entity;

import javax.persistence.*;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private String password;
  private String fullname;

  public User() {
  }

  public int getId() {
    return id;
  }

  public User setId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFullname() {
    return fullname;
  }

  public User setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }
}