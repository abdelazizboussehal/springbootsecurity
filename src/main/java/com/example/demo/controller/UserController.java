package com.example.demo.controller;

import com.example.demo.repositories.UserRepository;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public User storeUser (@RequestBody User user){
        user.setActive(true);
        user.setRoles("ADMIN");
        return userRepository.save(user);
    }
}
