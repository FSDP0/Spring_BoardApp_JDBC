package com.boardapp.boardapi.user.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardapp.boardapi.user.model.UserDto;
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
    private List<UserDto> findAllUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/:{userId}")
    private UserDto findUserById(@PathVariable String userId) {
        return this.userService.getUserById(userId);
    }

    @PostMapping
    private void createUser(@RequestBody UserDto dto) {
        this.userService.saveUser(dto);
    }

    @PutMapping("/:{userId}")
    private void editUser(@PathVariable String userId, @RequestBody UserDto dto) {
        this.userService.modifyUser(userId, dto);
    }

    @DeleteMapping("/:{userId}")
    private void deleteUser(@PathVariable String userId) {
        this.userService.removeUser(userId);
    }
}
