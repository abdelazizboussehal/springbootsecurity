package com.example.demo.modeles.annonce;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@Entity
@Table(name ="photos")
public class Photo {
    @Id
    private int id;
    @Column(columnDefinition="blob")
    private File image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Photo(int id, File image) {
        this.id = id;
        this.image = image;
    }

    public Photo() {
    }
}
