package com.project.controllers;
import java.util.List;
import java.util.Optional;

import com.project.services.UserServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entites.User;
import com.project.repos.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServices userServices;
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userServices.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userServices.saveOneUser(newUser);

    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        return userServices.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser) {
        return userServices.updateOneUser(userId,newUser);

    }
    @DeleteMapping("/userId")
    public void deleteOneUser(@PathVariable Long userId) {
        userServices.deleteOneUser(userId);
    }

}
