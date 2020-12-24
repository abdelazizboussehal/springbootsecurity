package com.example.demo.security;

import com.example.demo.user.User;
import com.example.demo.user.UserRopository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    final
    UserRopository userRepository;

    public MyUserDetailsService(UserRopository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // get user from database
        Optional<User> user = userRepository.findByUserName(userName);
        //if user does not exist
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        // create user details and return it
        return user.map(MyUserDetails::new).get();
}}
