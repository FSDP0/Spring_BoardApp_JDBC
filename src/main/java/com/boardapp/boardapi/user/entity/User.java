package com.boardapp.boardapi.user.entity;

import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
public class User {
    private String userId;
    private String userName;
    private String userPassword;

    private Date createdDate;
    private Date modifiedDate;

    @Builder
    public User(String id, String name, String password, Date createdDate, Date modifiedDate) {
        this.userId = id;
        this.userName = name;
        this.userPassword = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
