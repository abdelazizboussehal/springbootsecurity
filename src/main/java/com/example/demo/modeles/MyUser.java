package com.example.demo.modeles;

import com.example.demo.modeles.annonce.Comment;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MyUser {
    @Id
    private String id;
    @Column(unique=true,name = "user_name")
    private String userName;
    private String password;
    private String role;
    private int active;

    @OneToMany(mappedBy="ruser",cascade = CascadeType.ALL)
    private Set<Comment> rcomment;

    public MyUser(String id, String userName, String password, String role, int active) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public MyUser() {
    }

    public MyUser(String id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

}
