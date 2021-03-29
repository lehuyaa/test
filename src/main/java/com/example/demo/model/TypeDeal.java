package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(	name = "type_deal")
public class TypeDeal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeName;

    public TypeDeal() {
    }

    public TypeDeal(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
