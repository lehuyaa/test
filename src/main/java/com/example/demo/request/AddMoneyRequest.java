package com.example.demo.request;

public class AddMoneyRequest {

    private Long bankID;
    private Long money;
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBankID() {
        return bankID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
