package com.senai.teacherapp.Models;

public class SchoolClass {
    private Integer idClass;
    private String nameClass;
    private Integer quantityStudent;

    public SchoolClass() {
    }

    public SchoolClass(Integer idClass, String nameClass, Integer quantityStudent) {
        this.idClass = idClass;
        this.nameClass = nameClass;
        this.quantityStudent = quantityStudent;
    }

    public SchoolClass(String nameClass, Integer quantityStudent) {
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
