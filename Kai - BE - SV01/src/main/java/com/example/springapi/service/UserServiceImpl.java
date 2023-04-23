package com.example.springapi.service;

import com.example.springapi.dto.UserDTO;
import com.example.springapi.entity.UserEntity;
import com.example.springapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Value("${secret.code}")
    String secretPsw;

    @Override
    public UserEntity save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        userEntity.setEmail(userDTO.getEmail());
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity saveEmail(String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(email);
        userEntity.setPassword(bcryptEncoder.encode(secretPsw));
        userEntity.setEmail(email);
        userEntity.setProvider("Google");
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserByMail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isEmailAlready(String email) {
        return userRepository.existsByEmail(email);
    }

}
