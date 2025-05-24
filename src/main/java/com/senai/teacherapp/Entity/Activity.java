package com.senai.teacherapp.Entity;

import java.time.LocalDate;

public class Activity {
    private Integer idActivity;
    private String nameActivity;
    private String descriptionActivity;
    private LocalDate dateActivity;
    private Double weightActivity;

    public Activity() {
    }

    public Activity(Integer idActivity, String nameActivity, String descriptionActivity, LocalDate dateActivity, Double weightActivity) {
        this.idActivity = idActivity;
        this.nameActivity = nameActivity;
        this.descriptionActivity = descriptionActivity;
        this.dateActivity = dateActivity;
        this.weightActivity = weightActivity;
    }

    public Activity(String nameActivity, String descriptionActivity, LocalDate dateActivity, Double weightActivity) {
        this.nameActivity = nameActivity;
        this.descriptionActivity = descriptionActivity;
        this.dateActivity = dateActivity;
        this.weightActivity = weightActivity;
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
}
