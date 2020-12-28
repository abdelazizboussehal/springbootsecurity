package com.example.demo.services;

import com.example.demo.modeles.Cour;
import com.example.demo.modeles.annonce.Annonce;
import com.example.demo.repositories.CrudCour;
import com.example.demo.repositories.annonce.CrudAnnonce;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
1 create jsxml file
2 check naming
3 impliment this srvice
* */

@Service
public class ReportServiceAd {

    @Autowired
    private CrudAnnonce repository;

    // Choose format to generate file pdf or html
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        //path where we store finale file
        String path = "/home/aziz/Téléchargements/";
        // Get all of cours from database
        List<Annonce> cours=new ArrayList<>();
        repository.findAll().forEach(cours::add);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ads.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cours);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Abdelaziz Boussehal");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"annonce.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"annonce.pdf");
        }

        return path+ "annonce." ;
    }
}

