package com.zero.springsecurity.todo.entity;

import com.zero.springsecurity.authentication.entity.User;

import javax.persistence.*;

@Entity
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String content;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  public Todo() {
  }

  public int getId() {
    return id;
  }

  public Todo setId(int id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Todo setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public Todo setContent(String content) {
    this.content = content;
    return this;
  }

  public User getUser() {
    return user;
  }

  public Todo setUser(User user) {
    this.user = user;
    return this;
  }
}