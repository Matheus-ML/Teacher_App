package com.senai.teacherapp.Models;

import java.time.LocalDate;

//Classe de Activity com construtores, getters e setters.
public class Activity {
    private Integer idActivity;
    private String nameActivity;
    private String descriptionActivity;
    private LocalDate dateActivity;
    private Double weightActivity;
    private Integer idClass;

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public void setDescriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    public void setDateActivity(LocalDate dateActivity) {
        this.dateActivity = dateActivity;
    }

    public void setWeightActivity(Double weightActivity) {
        this.weightActivity = weightActivity;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public Activity() {
    }

    public Activity(Integer idActivity, String nameActivity, String descriptionActivity, LocalDate dateActivity, Double weightActivity) {
        this.idActivity = idActivity;
        this.nameActivity = nameActivity;
        this.descriptionActivity = descriptionActivity;
        this.dateActivity = dateActivity;
        this.weightActivity = weightActivity;
    }

    public Activity(String nameActivity, String descriptionActivity, LocalDate dateActivity, Double weightActivity, Integer idClass) {
        this.nameActivity = nameActivity;
        this.descriptionActivity = descriptionActivity;
        this.dateActivity = dateActivity;
        this.weightActivity = weightActivity;
        this.idClass = idClass;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public String getDescriptionActivity() {
        return descriptionActivity;
    }

    public LocalDate getDateActivity() {
        return dateActivity;
    }

    public Double getWeightActivity() {
        return weightActivity;
    }

    public Integer getIdClass() {
        return idClass;
    }
}
