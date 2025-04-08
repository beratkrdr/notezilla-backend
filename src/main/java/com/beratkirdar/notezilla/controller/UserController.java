package com.beratkirdar.notezilla.controller;

import com.beratkirdar.notezilla.entity.User;
import com.beratkirdar.notezilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.register(user);
    }

}
