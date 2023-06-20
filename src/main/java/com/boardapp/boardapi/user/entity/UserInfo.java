package com.boardapp.boardapi.user.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {
    private String userId;
    private String userName;
    private String userPassword;
    private String userPhoneNumber;

    private String userAddress;
    private String addressZipCode;

    private Date createdDate;
    private Date modifiedDate;

    @Builder
    public UserInfo(String id, String name, String password, String phoneNumber,
            String address, String zipcode, Date createdDate, Date modifiedDate) {
        this.userId = id;
        this.userName = name;
        this.userPassword = password;
        this.userPhoneNumber = phoneNumber;
        this.userAddress = address;
        this.addressZipCode = zipcode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
