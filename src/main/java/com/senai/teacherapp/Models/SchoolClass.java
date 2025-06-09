package com.senai.teacherapp.Models;

public class SchoolClass {
    private int idSchoolClass;
    private String nameSchoolClass;
    private Integer quantityStudent;

    public SchoolClass() {
    }

    public SchoolClass(int idSchoolClass, String nameSchoolClass, Integer quantityStudent) {
        this.idSchoolClass = idSchoolClass;
        this.nameSchoolClass = nameSchoolClass;
        this.quantityStudent = quantityStudent;
    }

    public SchoolClass(String nameSchoolClass, Integer quantityStudent) {
        this.nameSchoolClass = nameSchoolClass;
        this.quantityStudent = quantityStudent;
    }

    public int getIdSchoolClass() {
        return idSchoolClass;
    }

    public String getNameSchoolClass() {
        return nameSchoolClass;
    }

    public Integer getQuantityStudent() {
        return quantityStudent;
    }

    @Override
    public String toString() {
        return "Turma: " + nameSchoolClass + "Quantidade de Alunos: " + quantityStudent + "\n";
    }
}
