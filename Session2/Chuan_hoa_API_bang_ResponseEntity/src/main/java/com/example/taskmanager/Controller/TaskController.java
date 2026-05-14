package com.example.taskmanager.Controller;


import com.example.taskmanager.Models.Task;
import com.example.taskmanager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false) String search
    ) {

        List<Task> tasks = taskService.getAllTasks();

        if (search != null && !search.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task ->
                            task.getTitle().toLowerCase()
                                    .contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(tasks);
    }
}
