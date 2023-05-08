package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;

public interface UserService {

    UserEntity saveUser(String username);

    boolean isUserNameAlready(String username);
}
