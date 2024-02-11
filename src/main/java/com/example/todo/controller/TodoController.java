/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.todo.controller;

import com.example.todo.service.TodoH2Service;
import com.example.todo.model.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class TodoController {

    @Autowired
    private TodoH2Service todoService;

    @GetMapping("/todos")
    public ArrayList<Todo> getTodos() {

        return todoService.getTodos();
    }

    @GetMapping("/todos/{id}")
    public Todo getByid(@PathVariable("id") int id) {

        return todoService.getByid(id);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {

        return todoService.addTodo(todo);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {

        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") int id) {

        todoService.deleteTodo(id);
    }
}