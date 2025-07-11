package com.beratkirdar.notezilla.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(String email);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
