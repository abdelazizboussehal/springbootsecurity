package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mainController {

    @RequestMapping("/")
    public @ResponseBody String all(){
        return "<h1> i am all</h1>";
    }

    @RequestMapping("/user")
    public @ResponseBody String user(){
        return "<h1> i am user</h1>";
    }

    @RequestMapping("/admin")
    public @ResponseBody String admin(){
        return "<h1> i am Admin</h1>";
    }


}
