package com.edu.fly.sercurity.jwt;

import com.edu.fly.sercurity.TokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * The token provider.
     */
    private TokenProvider tokenProvider;

    /**
     * Instantiates a new jwt configurer.
     *
     * @param tokenProvider the token provider
     */
    public JWTConfigurer(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.config.annotation.SecurityConfigurerAdapter#
     * configure(org.springframework.security.config.annotation.SecurityBuilder)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JWTFilter customFilter = new JWTFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
