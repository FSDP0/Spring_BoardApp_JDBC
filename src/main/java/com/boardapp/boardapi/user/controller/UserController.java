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
import com.boardapp.boardapi.user.model.UserInfoDto;
import com.boardapp.boardapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User API", description = "User CRUD methods")
@RestController
@RequestMapping("users")
public class UserController {

    // Constructor Dependency Injection
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //

    @Operation(summary = "모든 사용자 정보 조회", description = "등록되어있는 모든 사용자 정보들을 조회합니다.")
    @GetMapping
    private List<UserInfoDto> findAllUsers() {
        return this.userService.getAllUserInfo();
    }

    @Operation(summary = "특정 사용자 정보 조회", description = "등록되어있는 특정 사용자를 사용자ID를 통해 조회합니다.")
    @GetMapping("/:{userId}")
    private UserInfoDto findUserById(@PathVariable String userId) {
        return this.userService.getUserInfoById(userId);
    }

    @Operation(summary = "사용자 정보 등록", description = "새로운 사용자 정보를 등록합니다.")
    @PostMapping
    private void createUser(@RequestBody UserInfoDto dto) {
        this.userService.saveUserInfo(dto);
    }

    @Operation(summary = "사용자 정보 수정", description = "등록되어있는 기존 사용자 정보를 수정합니다.")
    @PutMapping("/:{userId}")
    private void editUser(@PathVariable String userId, @RequestBody UserInfoDto dto) {
        this.userService.modifyUserInfo(userId, dto);
    }

    @Operation(summary = "사용자 정보 삭제", description = "등록되어있는 기존 사용자 정보를 삭제합니다.")
    @DeleteMapping("/:{userId}")
    private void deleteUser(@PathVariable String userId) {
        this.userService.removeUserInfo(userId);
    }
}
