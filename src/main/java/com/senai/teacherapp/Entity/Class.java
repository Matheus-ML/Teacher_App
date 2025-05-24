package com.senai.teacherapp.Entity;

public class Class {
    private Integer idClass;
    private String nameClass;
    private Integer quantityStudent;

    public Class() {
    }

    public Class(Integer idClass, String nameClass, Integer quantityStudent) {
        this.idClass = idClass;
        this.nameClass = nameClass;
        this.quantityStudent = quantityStudent;
    }

    public Class(String nameClass, Integer quantityStudent) {
        this.nameClass = nameClass;
        this.quantityStudent = quantityStudent;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public Integer getQuantityStudent() {
        return quantityStudent;
    }
}
