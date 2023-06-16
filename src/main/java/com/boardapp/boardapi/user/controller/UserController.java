package com.boardapp.boardapi.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardapp.boardapi.user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    // Constructor Dependency Injection
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //

    @GetMapping
    private void findAllUsers() {

    }

    @GetMapping("/:{userId}")
    private void findUserById(@PathVariable String userId) {

    }

    @PostMapping
    private void createUser() {

    }

    @PutMapping("/:{userId}")
    private void editUser(@PathVariable String userId) {

    }

    @DeleteMapping("/:{userId}")
    private void deleteUser(@PathVariable String userId) {

    }
}
