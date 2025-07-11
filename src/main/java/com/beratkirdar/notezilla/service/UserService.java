package com.beratkirdar.notezilla.service;

import com.beratkirdar.notezilla.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> register(User user);

    ResponseEntity<String> login(User user);

    User getCurrentUser();

}
