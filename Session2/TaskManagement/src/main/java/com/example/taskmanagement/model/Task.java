package com.example.taskmanagement.model;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Task {
    private Integer id;
    private String title;
    private String description;
    private String priority;
    private int assignedTo;
}
