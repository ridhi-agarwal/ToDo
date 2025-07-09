package com.todo.todo.Service;

import com.todo.todo.Entity.Task;
import com.todo.todo.Repository.TaskRepository;
import com.todo.todo.dto.TodosResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TodosResponse getAllTodos(){
         List<Task> todos = taskRepository.findAll();
         return new TodosResponse(todos);

    }
    public Task createTodo(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setComplete(false);
        taskRepository.save(task);
        return task;
    }
    public ResponseEntity<Object> updateTodo(long id, String updatedTitle) {
        Optional<Task> existingTodo = taskRepository.findById((int)id);
        if (existingTodo.isEmpty()) {
            System.out.println("Todo Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (existingTodo.get().getTitle().equals(updatedTitle)) {
                System.out.println("Existing Title is same as New Title");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                existingTodo.get().setTitle(updatedTitle);
                taskRepository.save(existingTodo.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    }
    public ResponseEntity<Object> deleteTodo(long id) {
        Optional<Task> todo = taskRepository.findById((int)id);
        if (todo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById((int)id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity updateTodoStatus(long id) {
        Optional<Task> existingTodo = taskRepository.findById((int)id);
        if (existingTodo.isEmpty()) {
            System.out.println("Todo Not Found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (existingTodo.get().getComplete() == Boolean.TRUE) {
            System.out.println("Todo Already Completed");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        existingTodo.get().setComplete(true);
        taskRepository.save(existingTodo.get());
        return new ResponseEntity(HttpStatus.OK);
    }
}
