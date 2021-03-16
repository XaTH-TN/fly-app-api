package com.edu.fly.service;

import com.edu.fly.dto.JWTAuthDTO;
import com.edu.fly.dto.LoginDTO;

public interface AuthenticationService {

    JWTAuthDTO authentication(LoginDTO loginDTO);

}
