package com.example.demo.controller;

import com.example.demo.exeptions.ResourceNotFoundException;
import com.example.demo.modeles.annonce.Annonce;
import com.example.demo.repositories.annonce.CrudAnnonce;
import com.example.demo.repositories.annonce.CrudPhoto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class AnnonceController {
    private final CrudAnnonce crudAnnonce;
    private final CrudPhoto crudPhoto;

    public AnnonceController(CrudAnnonce crudAnnonce, CrudPhoto crudPhoto) {
        this.crudAnnonce = crudAnnonce;
        this.crudPhoto = crudPhoto;
    }

    @RequestMapping("/annonce")
    public  List<Annonce> annonceList(){
        return (List<Annonce>) crudAnnonce.findAll();
    }

    @RequestMapping("/annonce/{id}")
    public  Annonce annonce(@PathVariable int id)  {
        return crudAnnonce
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "annonce with this: "+id+" id not found"));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/annonce")
    // Authority only for writing
//    @PreAuthorize("hasAnyAuthority('annonce:write')")
    public Annonce annonce(@RequestBody Annonce annonce){
        try {
            annonce.getRphoto().forEach(crudPhoto::save);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return crudAnnonce.save(annonce);
    }
}
