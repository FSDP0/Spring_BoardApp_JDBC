package com.boardapp.boardapi.user.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserAddress {
    private String userId;
    private String userAddress;
    private String addressZipCode;

    @Builder
    public UserAddress(String id, String address, String zipCode) {
        this.userId = id;
        this.userAddress = address;
        this.addressZipCode = zipCode;
    }
}
