package com.beratkirdar.notezilla.controller;

import com.beratkirdar.notezilla.entity.User;
import com.beratkirdar.notezilla.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Authentication", description = "Operations related to user authentication and registration")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Authenticates a user with email and password, and returns a JWT token if credentials are valid."
    )
    public ResponseEntity<String> login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    @Operation(
            summary = "User Registration",
            description = "Registers a new user with name, email, and password. Returns a success message upon completion."
    )
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.register(user);
    }

}
