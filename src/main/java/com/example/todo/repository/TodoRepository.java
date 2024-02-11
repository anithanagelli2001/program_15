// Write your code here
package com.example.todo.repository;

import com.example.todo.model.Todo;

import java.util.*;

public interface TodoRepository {

    ArrayList<Todo> getTodos();

    Todo getByid(int id);

    Todo addTodo(Todo todo);

    Todo updateTodo(int id, Todo todo);

    void deleteTodo(int id);
}
