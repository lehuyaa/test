package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(	name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankNumber;
    private Long idUser;
    private Long moneyNumber;
    private String password;
    private String bankName;
    private String colorATMCard;

    public BankAccount() {
    }

    public BankAccount(Long id, String bankNumber, Long idUser, Long moneyNumber, String password, String bankName, String colorATMCard) {
        this.id = id;
        this.bankNumber = bankNumber;
        this.idUser = idUser;
        this.moneyNumber = moneyNumber;
        this.password = password;
        this.bankName = bankName;
        this.colorATMCard = colorATMCard;
    }


    public String getColorATMCard() {
        return colorATMCard;
    }

    public void setColorATMCard(String colorATMCard) {
        this.colorATMCard = colorATMCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
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

    public Long getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(Long moneyNumber) {
        this.moneyNumber = moneyNumber;
    }
}
