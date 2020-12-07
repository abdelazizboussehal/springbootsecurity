package com.example.demo.modeles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyUser {
    @Id
    private String id;
    @Column(unique=true)
    private String userName;
    private String Password;
    private String role;
    private int active;

    public MyUser(String id, String userName, String password, String role, int active) {
        this.id = id;
        this.userName = userName;
        Password = password;
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
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
        Password = password;
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
