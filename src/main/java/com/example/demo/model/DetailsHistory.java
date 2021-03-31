package com.example.demo.model;

import java.sql.Date;

public class DetailsHistory {
    private String nameFrom;
    private String nameTo;
    private String content;
    private Long money;
    private Date date;
    public DetailsHistory() {
    }

    public DetailsHistory(String nameFrom, String nameTo, String content, Long money, Date date) {
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.content = content;
        this.money = money;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
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
}
