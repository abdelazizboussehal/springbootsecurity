package com.example.demo.modeles.annonce;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="annonces")
public class Annonce {
    @Id
    private int id;
    private int price;
    private String type;
    @Column(name = "product_name")
    private String productName;
    private String categorise;
    private String description;
    @Column(name = "technical_form")
    private String technicalFrom;
    private Double longitude;
    private Double latitude;
    @Column(name = "creation_date")
    private Date creationDate;

    @OneToMany
    @JoinColumn(name="annonce_id")
    private Set<Photo> rphoto;

    @OneToMany(mappedBy="rannonce", cascade=CascadeType.ALL)
    private Set<Comment> rcomment;

    public Annonce() {
    }


    public Set<Photo> getRphoto() {
        return rphoto;
    }

    public void setRphoto(Set<Photo> rphoto) {
        this.rphoto = rphoto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorise() {
        return categorise;
    }

    public void setCategorise(String categorise) {
        this.categorise = categorise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnicalFrom() {
        return technicalFrom;
    }

    public void setTechnicalFrom(String technicalFrom) {
        this.technicalFrom = technicalFrom;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
