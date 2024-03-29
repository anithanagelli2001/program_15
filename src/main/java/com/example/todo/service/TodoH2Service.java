/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import com.example.todo.model.Todo;

import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class TodoH2Service implements TodoRepository {

    @Autowired
    private JdbcTemplate db;

    public ArrayList<Todo> getTodos() {
        return (ArrayList<Todo>) db.query("select * from todoList", new TodoRowMapper());
    }

    public Todo getByid(int id) {
        try {
            return db.queryForObject("select * from todoList where id = ?", new TodoRowMapper(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Todo addTodo(Todo todo) {
        db.update("insert into todoList(todo, status, priority) values (?,?,?)",
                todo.getTodo(), todo.getStatus(), todo.getPriority());
        return db.queryForObject("select * from todoList where todo = ? and priority = ?", new TodoRowMapper(),
                todo.getTodo(), todo.getPriority());
    }

    public void deleteTodo(int id) {
        db.update("delete from todoList where id = ?", id);
    }

    public Todo updateTodo(int id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("update todoList set todo = ? where id =?", todo.getTodo(), id);
        }
        if (todo.getPriority() != null) {
            db.update("update todoList set priority = ? where id =?", todo.getPriority(), id);
        }
        if (todo.getStatus() != null) {
            db.update("update todoList set status = ? where id =?", todo.getStatus(), id);
        }
        return getByid(id);
    }
}
