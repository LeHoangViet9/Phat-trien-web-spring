package com.example.taskmanagement.services;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getTasks(){
        return taskRepository.getTasks();
    }
    public Task getTask(int id){
        return taskRepository.findById(id);
    }

    public void addTask(Task task){
        taskRepository.addTask(task);
    }
    public Task uppdateTask(int id,Task task){
        return taskRepository.save(id,task);
    }
    public boolean deleteTask(int id){
        return taskRepository.delete(id);
    }


}
