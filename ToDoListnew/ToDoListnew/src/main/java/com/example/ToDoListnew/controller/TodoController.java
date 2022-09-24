package com.example.ToDoListnew.controller;

import com.example.ToDoListnew.entity.Todo;
import com.example.ToDoListnew.request.CreateTodoRequest;
import com.example.ToDoListnew.request.UpdateTodoRequest;
import com.example.ToDoListnew.service.TodoService;
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
    public List<Todo> getTodos(@RequestParam Optional<Boolean> status){
        if(status.isPresent()){
            return todoService.getTodos(status.get());
        }
        return todoService.getTodos();
    }
    @PostMapping("/todos")
    public Todo createTodos(@RequestBody CreateTodoRequest createTodoRequest){
        return todoService.createTodo(createTodoRequest);
    }
    @PutMapping("/todos/{id}")
    public Todo updateTodos(@PathVariable Integer id, @RequestBody UpdateTodoRequest updateTodoRequest){
        return todoService.updateTodo(id,updateTodoRequest);
    }
    @DeleteMapping("/todos/{id}")
    public void deleteTodos(@PathVariable Integer id){
        todoService.deleteTodo(id);
    }
}
