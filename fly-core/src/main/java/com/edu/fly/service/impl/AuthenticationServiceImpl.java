package com.edu.fly.service.impl;
import com.edu.fly.config.ConstantKey;
import com.edu.fly.dto.JWTAuthDTO;
import com.edu.fly.dto.LoginDTO;
import com.edu.fly.exception.NotFoundException;
import com.edu.fly.sercurity.TokenProvider;
import com.edu.fly.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public JWTAuthDTO authentication(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            JWTAuthDTO response = JWTAuthDTO.builder().token(token).build();
            return response;
        } catch (Exception e) {
            throw new NotFoundException(ConstantKey.MSG_USERNAME_PASSWORD_NOT_MATCH);
        }
    }

}
