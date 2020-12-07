package com.example.demo.security;

import com.example.demo.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    public User user;
    public MyUserDetails(User user) {
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] rolesList= user.getRoles().split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList=new ArrayList<>();
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return "root";
    }

    @Override
    public String getUsername() {
        return "root";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }
}
