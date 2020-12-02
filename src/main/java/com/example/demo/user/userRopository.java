package com.example.demo.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface userRopository extends CrudRepository<User, Integer> {
    Optional <User> findByUserName(String userName);
}
