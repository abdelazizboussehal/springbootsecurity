package com.example.demo.controller;

import com.example.demo.modeles.Cour;
import com.example.demo.repositories.CrudCour;
import com.example.demo.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CoursController {

    @Autowired
    private CrudCour crudCour;

    @Autowired
    private ReportService reportService;

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



    @RequestMapping("/report/{type}")
    public @ResponseBody String report(@PathVariable("type") String type) throws FileNotFoundException, JRException {
        return reportService.exportReport(type);

    }

}
