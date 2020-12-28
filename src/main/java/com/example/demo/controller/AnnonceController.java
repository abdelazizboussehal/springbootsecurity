package com.example.demo.controller;

import com.example.demo.exeptions.ResourceNotFoundException;
import com.example.demo.modeles.annonce.Annonce;
import com.example.demo.repositories.annonce.CrudAnnonce;
import com.example.demo.repositories.annonce.CrudPhoto;
import com.example.demo.services.ReportServiceAd;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.List;

@Controller
@ResponseBody
public class AnnonceController {
    private final CrudAnnonce crudAnnonce;
    private final CrudPhoto crudPhoto;
    @Autowired
    ReportServiceAd reportServiceAd;

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
    @RequestMapping("annonce/report/{type}")
    public @ResponseBody
    ResponseEntity<File> report(@PathVariable("type") String type) throws FileNotFoundException, JRException {
        File f= new File(reportServiceAd.exportReport(type));
        return ResponseEntity.ok()
                .body(f);

    }
    /*@RequestMapping("annonce/reportd/{type}")
    public @ResponseBody
    void reporta(@PathVariable("type") String type, HttpServletResponse response) throws FileNotFoundException,
            JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" +"annonce."+type);
        response.setHeader("Content-Transfer-Encoding", "binary");
        String url = reportServiceAd.exportReport(type) + type;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(url);
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
            bos.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "annonce/reportd/{type}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("type") String type) throws IOException, JRException {
        String url = reportServiceAd.exportReport(type) + type;
        File file= new File(url);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
