package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(	name = "transfer")
public class TransferPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPhoneFrom;
    private Long  idPhoneTo;
    private Long money;
    private String content;

    public TransferPhone(Long id, Long idPhoneFrom, Long idPhoneTo, Long money, String content) {
        this.id = id;
        this.idPhoneFrom = idPhoneFrom;
        this.idPhoneTo = idPhoneTo;
        this.money = money;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getIdPhoneFrom() {
        return idPhoneFrom;
    }

    public void setIdPhoneFrom(Long idPhoneFrom) {
        this.idPhoneFrom = idPhoneFrom;
    }

    public Long getIdPhoneTo() {
        return idPhoneTo;
    }

    public void setIdPhoneTo(Long idPhoneTo) {
        this.idPhoneTo = idPhoneTo;
    }
}
