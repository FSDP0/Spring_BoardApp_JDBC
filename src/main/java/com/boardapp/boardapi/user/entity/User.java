package com.boardapp.boardapi.user.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public User(String id, String name, String password, String createdDate, String modifiedDate) {
        this.userId = id;
        this.userName = name;
        this.userPassword = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
