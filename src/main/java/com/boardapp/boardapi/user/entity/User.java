package com.boardapp.boardapi.user.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;

    private Date createdDate;
    private Date modifiedDate;

    @Builder
    public User(String id, String name, String password, String phoneNumber,
            Date createdDate, Date modifiedDate) {
        this.userId = id;
        this.userName = name;
        this.userPassword = password;
        this.userPhoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
