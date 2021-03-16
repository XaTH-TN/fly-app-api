package com.edu.fly.service.impl;

import com.edu.fly.entity.UsersEntity;
import com.edu.fly.repository.UserRepository;
import com.edu.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UsersEntity getByUserName(String username) {
        return userRepository.findByUsername(username).orElse(new UsersEntity());
    }

    @Override
    public UsersEntity getByUsernameAndPass(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
