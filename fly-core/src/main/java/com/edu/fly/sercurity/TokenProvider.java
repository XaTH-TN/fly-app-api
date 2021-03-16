package com.edu.fly.sercurity;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String NAME = "name";
    private String secretKey;
    private long tokenValidityInMilliseconds;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);


    public TokenProvider() { }

    @PostConstruct
    public void init() {
        this.secretKey = "CA87aNppNwHpQv3emfeHpH874JgnHd4Zcc9PAkg3";
        this.tokenValidityInMilliseconds = 86400 * 1000;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Get validity for token
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        String username = authentication.getName();

        return Jwts.builder().setSubject(username).claim(NAME, username).claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity).compact();
    }

    /**
     * Gets the authentication.
     *
     * @param token
     *            the token
     * @return the authentication
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsFromToken(token);

        if (claims == null) {
            return null;
        }

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * Validate token.
     *
     * @param authToken
     *            the auth token
     * @return true, if successful
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.info("Invalid JWT signature.");
            LOGGER.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            LOGGER.info("Invalid JWT token.");
            LOGGER.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            LOGGER.info("Expired JWT token.");
            LOGGER.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.info("Unsupported JWT token.");
            LOGGER.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            LOGGER.info("JWT token compact of handler are invalid.");
            LOGGER.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    /**
     * Gets the username from token.
     *
     * @param token
     *            the token
     * @return the username from token
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            LOGGER.info(e.toString());
            username = null;
        }
        return username;
    }

    /**
     * Gets the claims from token.
     *
     * @param token
     *            the token
     * @return the claims from token
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
