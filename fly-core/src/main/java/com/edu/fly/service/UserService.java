package com.edu.fly.service;

import com.edu.fly.entity.UsersEntity;


public interface UserService {

    UsersEntity getByUserName(String username);

    UsersEntity getByUsernameAndPass(String username, String password);
}
