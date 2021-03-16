package com.edu.fly.sercurity;

import com.edu.fly.entity.UsersEntity;
import com.edu.fly.service.UserService;
import com.edu.fly.support.PasswordEncryptSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        UsersEntity usersEntity = userService.getByUserName(username);

        if (usersEntity == null) {
            return null;

        }
        String passwordEncrypt = PasswordEncryptSupport.encryptBySha2(password);

        if (!passwordEncrypt.equalsIgnoreCase(usersEntity.getPassword())){
            log.error("Password not match!");
            return null;
        }
        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        return new UsernamePasswordAuthenticationToken(username, null, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
