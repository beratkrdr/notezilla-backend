package com.beratkirdar.notezilla.service.impl;

import com.beratkirdar.notezilla.entity.User;
import com.beratkirdar.notezilla.entity.UserPrincipal;
import com.beratkirdar.notezilla.repository.UserRepository;
import com.beratkirdar.notezilla.service.JwtService;
import com.beratkirdar.notezilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public ResponseEntity<String> register(User user) {
        try{

            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Register error! - "+e.getMessage());
        }

    }

    @Override
    public ResponseEntity<String> login(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (!authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect User email or password!");
        }

        return ResponseEntity.ok(jwtService.generateToken(user.getEmail()));
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String username = userPrincipal.getUsername();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
