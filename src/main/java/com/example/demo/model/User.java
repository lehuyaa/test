package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private Long moneyNumber;


    public User() {
    }

    public User(String username, String password,String name, Long moneyNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.moneyNumber = moneyNumber;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(Long moneyNumber) {
        this.moneyNumber = moneyNumber;
    }
}