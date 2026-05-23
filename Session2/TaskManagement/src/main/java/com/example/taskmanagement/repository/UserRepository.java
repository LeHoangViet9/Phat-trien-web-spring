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

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public User addUser(User user){
        users.add(user);
        return user;
    }

    public User updateUser(int id,User user){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==id){
                user.setId(id);
                users.set(i,user);
                return user;
            }
        }
        return null;
    }
    public void deleteUser(int id){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()==id){
                users.remove(i);
            }
        }
    }
}
