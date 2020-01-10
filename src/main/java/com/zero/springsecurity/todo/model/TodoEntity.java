package com.zero.springsecurity.todo.model;

import com.zero.springsecurity.authentication.model.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class TodoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String content;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  public TodoEntity() {
  }

  public int getId() {
    return id;
  }

  public TodoEntity setId(int id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public TodoEntity setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public TodoEntity setContent(String content) {
    this.content = content;
    return this;
  }

  public UserEntity getUserEntity() {
    return user;
  }

  public TodoEntity setUserEntity(UserEntity user) {
    this.user = user;
    return this;
  }
}