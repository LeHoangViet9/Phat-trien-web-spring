package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks=new ArrayList<>();
    public TaskRepository() {
        tasks.add(new Task(1,"Task 1","Desc 1","high",1));
        tasks.add(new Task(2,"Task 2","Desc 2","medium",1));
        tasks.add(new Task(3,"Task 3","Desc 3","low",2));
        tasks.add(new Task(4,"Task 4","Desc 4","high",2));
        tasks.add(new Task(5,"Task 5","Desc 5","medium",3));
        tasks.add(new Task(6,"Task 6","Desc 6","low",1));
        tasks.add(new Task(7,"Task 7","Desc 7","high",3));
        tasks.add(new Task(8,"Task 8","Desc 8","medium",2));
        tasks.add(new Task(9,"Task 9","Desc 9","low",1));
        tasks.add(new Task(10,"Task 10","Desc 10","high",3));
    }
    public List<Task> getTasks() {
        return tasks;
    }
}
