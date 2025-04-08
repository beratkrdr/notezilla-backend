package com.beratkirdar.notezilla.service.impl;

import com.beratkirdar.notezilla.entity.User;
import com.beratkirdar.notezilla.entity.UserPrincipal;
import com.beratkirdar.notezilla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return new UserPrincipal(user);

    }
}
