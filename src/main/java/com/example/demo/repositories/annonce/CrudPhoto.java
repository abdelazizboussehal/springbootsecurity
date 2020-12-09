package com.example.demo.repositories.annonce;

import com.example.demo.modeles.annonce.Photo;
import org.springframework.data.repository.CrudRepository;

public interface CrudPhoto extends CrudRepository<Photo,Integer> {
}
