package com.example.taskmanager.Models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    private int id;
    private String title;
    private String description;
    private String priority;
    private int assignedTo;
}
