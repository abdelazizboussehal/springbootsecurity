package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoursController {

    @Autowired
    private CrudCour crudCour;
    @RequestMapping("/cours")
    public @ResponseBody List<Cour> getAll(){
        List<Cour> cours=new ArrayList<>();
        crudCour.findAll().forEach(cours::add);
        return cours;
    }

    @RequestMapping(method = RequestMethod.POST , value = "/cours")
    public @ResponseBody List<Cour> addCour(@RequestBody Cour cour ){
        crudCour.save(cour);
        List<Cour> cours=new ArrayList<>();
        crudCour.findAll().forEach(cours::add);
        return cours;
    }
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
