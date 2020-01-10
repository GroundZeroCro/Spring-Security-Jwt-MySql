package com.zero.springsecurity.todo.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class TodoResponse implements Serializable {

  private int id;
  private String title;
  private String content;

  public TodoResponse(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public static List<TodoResponse> entitiesToResponses(List<TodoEntity> entities) {
    return entities.stream()
        .map(entity -> new TodoResponse(entity.getId(), entity.getTitle(), entity.getContent()))
        .collect(Collectors.toList());
  }

  public static TodoResponse entityToResponse(TodoEntity entity) {
    return new TodoResponse(entity.getId(), entity.getTitle(), entity.getContent());
  }

  public int getId() {
    return id;
  }

  public TodoResponse setId(int id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public TodoResponse setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public TodoResponse setContent(String content) {
    this.content = content;
    return this;
  }
}
