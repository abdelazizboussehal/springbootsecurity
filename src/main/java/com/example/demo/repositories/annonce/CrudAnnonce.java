package com.example.demo.repositories.annonce;

import com.example.demo.modeles.annonce.Annonce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudAnnonce extends CrudRepository<Annonce,Integer> {
}
