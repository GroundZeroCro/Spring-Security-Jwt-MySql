package com.zero.springsecurity.todo.controller;

import com.zero.springsecurity.todo.entity.Todo;
import com.zero.springsecurity.todo.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/todo")
class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public List<Todo> getTodo() {
        return this.todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Todo getSingleTodo(@PathVariable String id) throws Exception {
        return this.todoRepository.findById(Integer.parseInt(id)).orElseThrow(noItemWithIdException());
    }

    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) {
        return this.todoRepository.save(todo);
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