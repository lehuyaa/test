package com.example.demo.model;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(	name = "history")
public class History {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;
    private Long idDetails;
    private Long idTypeDeal;
    private String title;
    private String type;


    public History() {
    }

    public History(Long idUser, Long idDetails, Long idTypeDeal, String title, String type) {
        this.idUser = idUser;
        this.idDetails = idDetails;
        this.idTypeDeal = idTypeDeal;
        this.title = title;
        this.type = type;

    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(Long idDetails) {
        this.idDetails = idDetails;
    }

    public Long getIdTypeDeal() {
        return idTypeDeal;
    }

    public void setIdTypeDeal(Long idTypeDeal) {
        this.idTypeDeal = idTypeDeal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
