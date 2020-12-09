package com.example.demo.modeles.annonce;

import com.example.demo.modeles.MyUser;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private int id;
    private String content;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="annonce_id")
    private Annonce rannonce;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="comment_id")
    private MyUser ruser;

    public Comment(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Annonce getRannonce() {
        return rannonce;
    }

    public void setRannonce(Annonce rannonce) {
        this.rannonce = rannonce;
    }

    public MyUser getRuser() {
        return ruser;
    }

    public void setRuser(MyUser ruser) {
        this.ruser = ruser;
    }
}
