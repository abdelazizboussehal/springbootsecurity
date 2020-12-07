package com.example.demo.repositories;

import com.example.demo.modeles.MyUser;
import com.example.demo.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
