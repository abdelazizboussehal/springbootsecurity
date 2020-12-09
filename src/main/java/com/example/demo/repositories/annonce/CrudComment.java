package com.example.demo.repositories.annonce;

import com.example.demo.modeles.annonce.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CrudComment extends CrudRepository<Comment,Integer> {
}
