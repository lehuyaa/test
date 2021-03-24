package com.example.demo.response;

public class UserResponse {

    private Long id ;
    private String userName;
    private String name;
    private Long moneyNumber ;

    public UserResponse(Long id, String userName, String name, Long moneyNumber) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.moneyNumber = moneyNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
