package com.example.demo.response;

import com.example.demo.model.AddMoney;
import com.example.demo.model.DetailsHistory;
import com.example.demo.model.TransferToPhone;

public class HistoryResponse {

    private String title;
    private String type;
    private Long idTypeDeal;
    private DetailsHistory details;

    public HistoryResponse() {
    }

    public HistoryResponse(String title, String type, Long idTypeDeal, DetailsHistory details) {
        this.title = title;
        this.type = type;
        this.idTypeDeal = idTypeDeal;
        this.details = details;
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

    public Long getIdTypeDeal() {
        return idTypeDeal;
    }

    public void setIdTypeDeal(Long idTypeDeal) {
        this.idTypeDeal = idTypeDeal;
    }

    public DetailsHistory getDetails() {
        return details;
    }

    public void setDetails(DetailsHistory details) {
        this.details = details;
    }
}
