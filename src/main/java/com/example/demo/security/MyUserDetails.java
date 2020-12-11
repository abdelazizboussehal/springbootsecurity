package com.example.demo.security;

import com.example.demo.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    public User user;
    public MyUserDetails(User user) {
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // we can add here some authority : syntax cour:read
        String[] rolesList= user.getRoles().split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList=new ArrayList<>();
        for (String s:rolesList) {
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_"+s));
        }
        // add {{annonce:write}} authority to all users
        simpleGrantedAuthorityList
                .add(new SimpleGrantedAuthority("annonce:write"));
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
