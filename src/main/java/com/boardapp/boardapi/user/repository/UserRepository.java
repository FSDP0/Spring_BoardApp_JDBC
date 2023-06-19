package com.boardapp.boardapi.user.repository;

import java.util.List;
import com.boardapp.boardapi.user.entity.User;

public interface UserRepository {
    public List<User> findAllUsers();

    public User findUserById(String id);

    public void saveUser(User user);

    public void editUser(String id, User user);

    public void deleteUser(String id);
}
