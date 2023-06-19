package com.boardapp.boardapi.user.model;

import java.sql.Date;
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
    private Date createdDate;
    private Date modifiedDate;

    public User toEntity() {
        User user = User.builder().id(this.id).name(this.name).password(this.password)
                .createdDate(this.createdDate).modifiedDate(this.modifiedDate).build();

        return user;
    }

    @Builder
    public UserDto(String id, String name, String password, Date createDate, Date modifiedDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
