package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.services.TaskService;
import com.example.taskmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getTasks();
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        User user=userService.getUser(task.getAssignedTo());
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task newTask, @PathVariable int id){
        Task tasks=taskService.uppdateTask(id, newTask);
        if(tasks==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable int id){
        boolean tasks=taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
