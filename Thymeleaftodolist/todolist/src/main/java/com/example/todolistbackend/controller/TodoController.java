package com.example.todolistbackend.controller;

import com.example.todolistbackend.model.Todo;
import com.example.todolistbackend.request.CreateTodoRequest;
import com.example.todolistbackend.request.UpdateTodoRequest;
import com.example.todolistbackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam Optional<Boolean> status) {
        if (status.isPresent()) {
            return todoService.getTodos(status.get());
        }
        return todoService.getTodos();
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
