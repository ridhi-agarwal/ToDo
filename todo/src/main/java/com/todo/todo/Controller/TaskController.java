package com.todo.todo.Controller;

import com.todo.todo.Entity.Task;
import com.todo.todo.Service.TaskService;
import com.todo.todo.dto.TodosResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "/todos")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping()
    public ResponseEntity<TodosResponse> getTodos() {
        TodosResponse todosList = taskService.getAllTodos();
        return new ResponseEntity<>(todosList, HttpStatus.OK);
    }

    @PostMapping(path = "/task")
    public ResponseEntity<Task> createTodo(@RequestParam String title) {
        Task createdTodo = taskService.createTodo(title);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateTodo(@PathVariable("id") long id,
                                     @RequestParam(name = "title") String updatedTitle) {
        return taskService.updateTodo(id, updatedTitle);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteTodo(@PathVariable("id") long id) {
        return taskService.deleteTodo(id);
    }

    @PutMapping("/{id}/isCompleted")
    public ResponseEntity updateTodoStatus(@PathVariable("id") long id) {
        return taskService.updateTodoStatus(id);
    }


}
