package com.example.ToDoListnew.service;

import com.example.ToDoListnew.exception.BadRequestException;
import com.example.ToDoListnew.exception.NotFoundException;
import com.example.ToDoListnew.model.Todo;
import com.example.ToDoListnew.request.CreateTodoRequest;
import com.example.ToDoListnew.request.UpdateTodoRequest;
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
        todos = new ArrayList();
        todos.add(new Todo(1,"di choi",true));
        todos.add(new Todo(2,"Lam bai tap java",false));
        todos.add(new Todo(3,"da bong",false));
        todos.add(new Todo(4,"choi game",true));
    }
    //Lay danh sach tat ca cac cong viec
    public List<Todo> getTodos(){
        return todos;
    }
    //LAy danh sach tat ca cac cong viec theo trang thai
    public List<Todo> getTodos(boolean status){
        if(status){
            return todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
        }
        return todos.stream().filter(todo->!todo.isStatus()).collect(Collectors.toList());
    }
    //Tao cv
    public Todo createTodo(CreateTodoRequest createTodoRequest){
        if(createTodoRequest.getTitle().trim().equals("")){
            throw new BadRequestException("Không được để trống");
        }
        Random rd=new Random();
        Todo todo=new Todo(rd.nextInt(1000), createTodoRequest.getTitle(),false);
        todos.add(todo);
        return todo;
    }
    //Cap nhat cv
    public Todo updateTodo(int id, UpdateTodoRequest updateTodoRequest){
        Todo todo=findById(id).orElseThrow(()->{
            throw new NotFoundException("Không tìm thấy công việc có id ="+id) ;
        });
        todo.setTitle(updateTodoRequest.getTitle());
        todo.setStatus(updateTodoRequest.isStatus());
        return todo;
    }
    //Xoa cv
    public void deleteTodo(int id){
        Todo todo=findById(id).orElseThrow(()->{
           throw new NotFoundException("Không tìm thấy công việc có id ="+id) ;
        });
        todos.remove(todo);
    }
    //findByid
    public Optional<Todo> findById(int id){
        return todos.stream().filter(todo->todo.getId()==id).findFirst();
    }
}
