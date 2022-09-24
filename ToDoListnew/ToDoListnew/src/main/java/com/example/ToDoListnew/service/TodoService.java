package com.example.ToDoListnew.service;

import com.example.ToDoListnew.exception.BadRequestException;
import com.example.ToDoListnew.exception.NotFoundException;
import com.example.ToDoListnew.entity.Todo;
import com.example.ToDoListnew.repository.TodoRepository;
import com.example.ToDoListnew.request.CreateTodoRequest;
import com.example.ToDoListnew.request.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    //Lay danh sach tat ca cac cong viec
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    //LAy danh sach tat ca cac cong viec theo trang thai
    public List<Todo> getTodos(Boolean status){
        return todoRepository.findByStatus(status);
    }
    //Tao cv
    public Todo createTodo(CreateTodoRequest createTodoRequest){
        if(createTodoRequest.getTitle().trim().equals("")){
            throw new BadRequestException("Không được để trống");
        }

        return todoRepository.save(new Todo(createTodoRequest.getTitle()));
    }
    //Cap nhat cv
    public Todo updateTodo(Integer id, UpdateTodoRequest updateTodoRequest){
        Todo todo=todoRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException("Không tìm thấy công việc có id ="+id) ;
        });
        todo.setTitle(updateTodoRequest.getTitle());
        todo.setStatus(updateTodoRequest.getStatus());
        todoRepository.save(todo);
        return todo;
    }
    //Xoa cv
    public void deleteTodo(Integer id){
        Todo todo=todoRepository.findById(id).orElseThrow(()->{
           throw new NotFoundException("Không tìm thấy công việc có id ="+id) ;
        });
        todoRepository.delete(todo);
    }

}
