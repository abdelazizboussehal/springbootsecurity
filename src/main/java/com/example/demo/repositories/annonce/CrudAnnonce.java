package com.example.demo.repositories.annonce;

import com.example.demo.modeles.annonce.Annonce;
import org.springframework.data.repository.CrudRepository;

public interface CrudAnnonce extends CrudRepository<Annonce,Integer> {
}
