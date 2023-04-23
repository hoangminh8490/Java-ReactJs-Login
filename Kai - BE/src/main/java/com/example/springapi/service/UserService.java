package com.example.springapi.service;

import com.example.springapi.dto.UserDTO;
import com.example.springapi.entity.UserEntity;

public interface UserService {
    UserEntity save(UserDTO userDTO);

    UserEntity saveEmail(String email);

    UserEntity getUserByMail(String email);
    boolean isEmailAlready(String email);

}
