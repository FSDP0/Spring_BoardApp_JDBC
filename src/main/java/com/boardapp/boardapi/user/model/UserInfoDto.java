package com.boardapp.boardapi.user.model;

import java.util.Date;
import com.boardapp.boardapi.user.entity.User;
import com.boardapp.boardapi.user.entity.UserAddress;
import com.boardapp.boardapi.user.entity.UserInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {
    private String id;
    private String name;
    private String password;
    private String phoneNumber;

    private String address;
    private String zipCode;

    private Date createdDate;
    private Date modifiedDate;

    public User toUserEntity() {
        User user = User.builder().id(this.id).name(this.name).password(this.password)
                .phoneNumber(this.phoneNumber).build();

        return user;
    }

    public UserAddress toUserAddressEntity() {
        UserAddress userAddress = UserAddress.builder().id(this.id).address(this.address)
                .zipCode(this.zipCode).build();

        return userAddress;
    }

    public UserInfo toUserInfoEntity() {
        UserInfo userInfo = UserInfo.builder().id(this.id).name(this.name)
                .password(this.password).phoneNumber(this.phoneNumber)
                .address(this.address).zipcode(this.zipCode).build();

        return userInfo;
    }

    @Builder
    public UserInfoDto(String id, String name, String password, String phoneNumber,
            String address, String zipCode, Date createdDate, Date modifiedDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
