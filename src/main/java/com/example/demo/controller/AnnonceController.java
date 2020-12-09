package com.example.demo.controller;

import com.example.demo.modeles.annonce.Annonce;
import com.example.demo.repositories.annonce.CrudAnnonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AnnonceController {
    @Autowired
    CrudAnnonce crudAnnonce;

    @RequestMapping("/annonce/")
    public List<Annonce> annonceList(){
        return (List<Annonce>) crudAnnonce.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/annonce/")
    public Annonce annonce(@RequestBody Annonce annonce){
        return crudAnnonce.save(annonce);
    }
}
