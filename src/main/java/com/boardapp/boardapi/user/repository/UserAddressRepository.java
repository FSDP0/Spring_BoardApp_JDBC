package com.boardapp.boardapi.user.repository;

import java.util.List;
import com.boardapp.boardapi.user.entity.UserAddress;

public interface UserAddressRepository {
    public List<UserAddress> findAllUserAddress();
}
