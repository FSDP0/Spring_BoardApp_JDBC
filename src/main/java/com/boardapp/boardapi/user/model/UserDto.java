package com.boardapp.boardapi.user.model;

import java.time.LocalDateTime;
import com.boardapp.boardapi.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String id;
    private String name;
    private String password;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public User toEntity() {
        User user = User.builder().id(this.id).name(this.name).password(this.password)
                .phoneNumber(this.phoneNumber).createdDate(this.createdDate)
                .modifiedDate(this.modifiedDate).build();

        return user;
    }

    @Builder
    public UserDto(String id, String name, String password, String phoneNumber,
            LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
