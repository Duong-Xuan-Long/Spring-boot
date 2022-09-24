package com.example.ToDoListnew;

import com.example.ToDoListnew.entity.Todo;
import com.example.ToDoListnew.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ToDoListnewApplicationTests {
	@Autowired
	TodoRepository todoRepository;
	@Test
	void save_todo() {
		List todos = new ArrayList();
		todos.add(new Todo("di choi"));
		todos.add(new Todo(2,"Lam bai tap java",false));
		todos.add(new Todo(3,"da bong",false));
		todos.add(new Todo(4,"choi game",true));
		todoRepository.saveAll(todos);
	}

}
