package com.senai.teacherapp.Models;

public class SchoolClass {
    private Integer idSchoolClass;
    private String nameSchoolClass;
    private Integer quantityStudent;

    public SchoolClass() {
    }

    public SchoolClass(Integer idSchoolClass, String nameSchoolClass, Integer quantityStudent) {
        this.idSchoolClass = idSchoolClass;
        this.nameSchoolClass = nameSchoolClass;
        this.quantityStudent = quantityStudent;
    }

    public SchoolClass(String nameSchoolClass, Integer quantityStudent) {
        this.nameSchoolClass = nameSchoolClass;
        this.quantityStudent = quantityStudent;
    }

    public Integer getIdSchoolClass() {
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
