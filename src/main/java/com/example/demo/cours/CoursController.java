package com.example.demo.cours;

import com.example.demo.security.ReportService;
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


    @RequestMapping("/report/{type}")
    public @ResponseBody String report(@PathVariable("type") String type) throws FileNotFoundException, JRException {
        return reportService.exportReport(type);

    }

}
