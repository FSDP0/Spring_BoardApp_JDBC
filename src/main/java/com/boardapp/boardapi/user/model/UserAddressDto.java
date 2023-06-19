package com.boardapp.boardapi.user.model;

import com.boardapp.boardapi.user.entity.UserAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDto {
    private String id;
    private String address;
    private String zipCode;

    public UserAddress toEntity() {
        UserAddress userAddress = UserAddress.builder().id(this.id).address(this.address)
                .zipCode(this.zipCode).build();

        return userAddress;
    }

    @Builder
    public UserAddressDto(String id, String address, String zipCode) {
        this.id = id;
        this.address = address;
        this.zipCode = zipCode;
    }
}
