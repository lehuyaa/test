package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(	name = "add_money")
public class AddMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private Long money;
    private String content;
    private Long bankId;
    private Date date;

    public AddMoney() {
    }

    public AddMoney(Long idUser, Long money, String content, Long bankId, Date date) {
        this.idUser = idUser;
        this.money = money;
        this.content = content;
        this.bankId = bankId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
