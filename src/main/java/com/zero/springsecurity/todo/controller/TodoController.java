package com.zero.springsecurity.todo.controller;

import com.zero.springsecurity.authentication.entity.User;
import com.zero.springsecurity.authentication.repository.UserInfoRepository;
import com.zero.springsecurity.authentication.utils.JwtTokenUtil;
import com.zero.springsecurity.todo.entity.Todo;
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
  public List<Todo> getTodo() {
    return this.todoRepository.findAll();
  }

  @GetMapping("/{id}")
  public Todo getSingleTodo(@PathVariable String id) throws Exception {
    return this.todoRepository.findById(Integer.parseInt(id)).orElseThrow(noItemWithIdException());
  }

  @PostMapping("/add")
  public Todo addTodo(@RequestBody Todo todo, @RequestHeader(value = "Authorization") String authorization) {
    todo.setUser(getUser(authorization));
    return this.todoRepository.save(todo);
  }

  private User getUser(String authorization) {
    String username = jwtTokenUtil.getUsernameFromToken(authorization.substring(7));
    return userInfoRepository.findByUsername(username);
  }

  @PutMapping("/{id}")
  public Todo updateTodo(@RequestBody Todo updatedTodo, @PathVariable String id) throws Exception {
    Todo previousTodo = this.todoRepository.findById(Integer.parseInt(id)).orElseThrow(noItemWithIdException());
    previousTodo.setTitle(updatedTodo.getTitle());
    previousTodo.setContent(updatedTodo.getContent());
    return this.todoRepository.save(previousTodo);
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