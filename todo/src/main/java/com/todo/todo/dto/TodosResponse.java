package com.todo.todo.dto;

import com.todo.todo.Entity.Task;

import java.util.List;

public class TodosResponse {
    private List<Task> todos;

    public TodosResponse(List<Task> todos) {
        this.todos = todos;
    }

    public List<Task> getTodos() {
        return todos;
    }

    public void setTodos(List<Task> todos) {
        this.todos = todos;
    }
}
