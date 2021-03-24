package com.example.demo.request;

public class TransferPhoneRequest {

    private String phoneFrom;
    private String phoneTo ;
    private Long money;
    private String password;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneFrom() {
        return phoneFrom;
    }

    public void setPhoneFrom(String phoneFrom) {
        this.phoneFrom = phoneFrom;
    }

    public String getPhoneTo() {
        return phoneTo;
    }

    public void setPhoneTo(String phoneTo) {
        this.phoneTo = phoneTo;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
