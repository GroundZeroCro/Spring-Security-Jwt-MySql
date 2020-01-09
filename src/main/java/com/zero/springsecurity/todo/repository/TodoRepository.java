package com.zero.springsecurity.todo.repository;

import com.zero.springsecurity.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}