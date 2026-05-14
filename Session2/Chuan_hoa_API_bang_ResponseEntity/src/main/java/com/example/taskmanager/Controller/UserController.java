package com.example.taskmanager.Controller;

import com.example.taskmanager.Models.User;
import com.example.taskmanager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String search) {
        List<User> users=userService.getAllUsers();
        if(search!=null&&!search.isEmpty()){
            users=users.stream().filter(user -> user.getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
