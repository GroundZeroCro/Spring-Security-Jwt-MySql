package com.zero.springsecurity.todo.controller;

import com.zero.springsecurity.authentication.model.UserEntity;
import com.zero.springsecurity.authentication.repository.UserInfoRepository;
import com.zero.springsecurity.authentication.utils.JwtTokenUtil;
import com.zero.springsecurity.todo.model.TodoEntity;
import com.zero.springsecurity.todo.model.TodoResponse;
import com.zero.springsecurity.todo.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/todo")
class TodoController {

  private final TodoRepository todoRepository;
  private final UserInfoRepository userInfoRepository;
  private final JwtTokenUtil jwtTokenUtil;

  public TodoController(TodoRepository todoRepository, UserInfoRepository userInfoRepository, JwtTokenUtil jwtTokenUtil) {
    this.todoRepository = todoRepository;
    this.userInfoRepository = userInfoRepository;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @GetMapping
  public List<TodoResponse> getTodo() {
    return TodoResponse.entitiesToResponses(this.todoRepository.findAll());
  }

  @GetMapping("/{id}")
  public TodoResponse getSingleTodo(@PathVariable String id) throws Exception {
    TodoEntity todoEntity = this.todoRepository.findById(Integer.parseInt(id)).orElseThrow(noItemWithIdException());
    return TodoResponse.entityToResponse(todoEntity);
  }

  @PostMapping("/add")
  public TodoEntity addTodo(@RequestBody TodoEntity todoEntity, @RequestHeader(value = "Authorization") String authorization) {
    todoEntity.setUserEntity(getUser(authorization));
    return this.todoRepository.save(todoEntity);
  }

  private UserEntity getUser(String authorization) {
    String username = jwtTokenUtil.getUsernameFromToken(authorization.substring(7));
    return userInfoRepository.findByUsername(username);
  }

  @PutMapping("/{id}")
  public boolean updateTodo(@RequestBody TodoEntity updatedTodoEntity, @PathVariable String id) throws Exception {
    TodoEntity previousTodoEntity = this.todoRepository.findById(Integer.parseInt(id)).orElseThrow(noItemWithIdException());
    previousTodoEntity.setTitle(updatedTodoEntity.getTitle());
    previousTodoEntity.setContent(updatedTodoEntity.getContent());
    this.todoRepository.save(previousTodoEntity);
    return true;
  }

  private Supplier<? extends Exception> noItemWithIdException() {
    return () -> new Exception("No todo with the required id");
  }

  @DeleteMapping("/{id}")
  public boolean deleteTodo(@PathVariable String id) {
    try {
      this.todoRepository.deleteById(Integer.parseInt(id));
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}