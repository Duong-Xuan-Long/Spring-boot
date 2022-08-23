package com.example.todolistbackend.service;

import com.example.todolistbackend.exception.BadRequestException;
import com.example.todolistbackend.exception.NotFoundException;
import com.example.todolistbackend.model.Todo;
import com.example.todolistbackend.request.CreateTodoRequest;
import com.example.todolistbackend.request.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        todos = new ArrayList<>();
        todos.add(new Todo(1, "Đi chơi", true));
        todos.add(new Todo(2, "Làm bài tập Java", false));
        todos.add(new Todo(3, "Đá bóng", false));
        todos.add(new Todo(4, "Chơi game", true));
    }

    // Lấy ds tất cả cv
    public List<Todo> getTodos() {
        return todos;
    }

    // Lấy ds tất cả cv theo trạng thái
    public List<Todo> getTodos(boolean status) {
        if (status) {
            return todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
        }
        return todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
    }

    // Tạo cv
    public Todo createTodo(CreateTodoRequest request) {
        if (request.getTitle().trim().equals("")) {
            throw new BadRequestException("Tiêu đề không được để trống");
        }

        Random rd = new Random();

        Todo todo = new Todo(rd.nextInt(1000), request.getTitle(), false);
        todos.add(todo);

        return todo;
    }

    // Cập nhật cv
    public Todo updateTodo(int id, UpdateTodoRequest request) {
        if (request.getTitle().trim().equals("")) {
            throw new BadRequestException("Tiêu đề không được để trống");
        }

        Todo todo = findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy cv có id = " + id);
        });

        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        return todo;
    }

    // Xóa cv
    public void deleteTodo(int id) {
        Todo todo = findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy cv có id = " + id);
        });

        todos.remove(todo);
    }

    // findById
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }
}
