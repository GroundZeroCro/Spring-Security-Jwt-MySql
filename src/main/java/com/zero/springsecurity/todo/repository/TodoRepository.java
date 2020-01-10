package com.zero.springsecurity.todo.repository;

import com.zero.springsecurity.todo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
}