package com.example.demo.controller;

import com.example.demo.exeptions.ResourceNotFoundException;
import com.example.demo.modeles.annonce.Annonce;
import com.example.demo.repositories.annonce.CrudAnnonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class AnnonceController {
    @Autowired
    CrudAnnonce crudAnnonce;

    @RequestMapping("/annonce")
    public  List<Annonce> annonceList(){
        return (List<Annonce>) crudAnnonce.findAll();
    }

    @RequestMapping("/annonce/{id}")
    public  Annonce annonce(@PathVariable int id)  {
        return (Annonce) crudAnnonce
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "annonce with this: "+id+" id not found"));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/annonce")
    // Authority only for writing
    @PreAuthorize("hasAnyAuthority('annonce:write')")
    public Annonce annonce(@RequestBody Annonce annonce){

        return crudAnnonce.save(annonce);
    }
}
