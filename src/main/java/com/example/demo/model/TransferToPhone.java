package com.example.demo.model;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(	name = "transfer_to_phone")
public class TransferToPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //người nhận
    private Long idTo;

    //người gửi
    private Long idFrom;
    private Long money;
    private String content;
    private Date date;

    public TransferToPhone() {
    }

    public TransferToPhone(Long idTo, Long idFrom, Long money, String content, Date date) {
        this.idTo = idTo;
        this.idFrom = idFrom;
        this.money = money;
        this.content = content;
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTo() {
        return idTo;
    }

    public void setIdTo(Long idTo) {
        this.idTo = idTo;
    }

    public Long getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(Long idFrom) {
        this.idFrom = idFrom;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
