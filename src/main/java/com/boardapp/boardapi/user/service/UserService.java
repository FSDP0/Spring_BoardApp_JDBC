package com.boardapp.boardapi.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardapi.user.entity.User;
import com.boardapp.boardapi.user.model.UserDto;
import com.boardapp.boardapi.user.repository.UserJdbcRepository;

@Service
public class UserService {

    private UserJdbcRepository userJdbcRepository;

    public UserService(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public List<UserDto> getUsers() {
        List<User> userList = this.userJdbcRepository.findAllUsers();

        if (userList.isEmpty()) {
            return null;
        }

        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (User user : userList) {
            UserDto userDto = UserDto.builder().id(user.getUserId())
                    .name(user.getUserName()).password(user.getUserPassword())
                    .phoneNumber(user.getUserPhoneNumber())
                    .createDate(user.getCreatedDate())
                    .modifiedDate(user.getModifiedDate()).build();

            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    public UserDto getUserById(String id) {
        User user = this.userJdbcRepository.findUserById(id);

        if (user == null) {
            return null;
        }

        UserDto userDto = UserDto.builder().id(user.getUserId()).name(user.getUserName())
                .password(user.getUserPassword()).phoneNumber(user.getUserPhoneNumber())
                .createDate(user.getCreatedDate()).modifiedDate(user.getModifiedDate())
                .build();

        return userDto;
    }

    public void saveUser(UserDto userDto) {
        this.userJdbcRepository.saveUser(userDto.toEntity());
    }

    public void modifyUser(String id, UserDto userDto) {
        User user = userDto.toEntity();

        this.userJdbcRepository.editUser(id, user);
    }

    public void removeUser(String id) {
        this.userJdbcRepository.deleteUser(id);
    }
}
