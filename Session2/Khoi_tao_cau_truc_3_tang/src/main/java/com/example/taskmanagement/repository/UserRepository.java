package com.example.taskmanagement.repository;

import com.example.taskmanagement.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users =new ArrayList<>();
    public UserRepository() {
        users.add(new User(1, "john", "john@gmail.com", "admin"));
        users.add(new User(2, "anna", "anna@gmail.com", "member"));
        users.add(new User(3, "david", "david@gmail.com", "member"));
    }
    public List<User> getUsers() {
        return users;
    }
}
