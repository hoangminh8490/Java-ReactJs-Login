package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity saveUser(String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(username);
        return userRepository.save(userEntity);
    }

    @Override
    public boolean isUserNameAlready(String username) {
        return userRepository.existsByUserName(username);
    }
}
