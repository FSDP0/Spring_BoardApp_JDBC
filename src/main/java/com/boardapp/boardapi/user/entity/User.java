package com.boardapp.boardapi.user.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public User(String id, String name, String password, String phoneNumber,
            LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.userId = id;
        this.userName = name;
        this.userPassword = password;
        this.userPhoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
