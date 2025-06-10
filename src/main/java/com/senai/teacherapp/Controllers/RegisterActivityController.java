package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.ActivityDAO;
import com.senai.teacherapp.Models.Activity;
import com.senai.teacherapp.Models.Notification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterActivityController {
    @FXML private DatePicker dateActivity;
    @FXML private TextArea txtDescriptionActivity;
    @FXML private TextField txtNameActivity;

    private int schoolClassId;

    public void setSchoolClassId(int schoolClassId){
        this.schoolClassId = schoolClassId;
    }

    @FXML
    void btnConfirmActivity(ActionEvent event) {
        LocalDate date = dateActivity.getValue();
        String description = txtDescriptionActivity.getText();
        String name = txtNameActivity.getText();
        if (description.isEmpty() || name.isEmpty() || dateActivity.getValue() == null){
            new Notification().ErrorAlert("Campos vázios", "Por favor preencha todos os campos!");
            return;
        }

        Activity act = new Activity(name,description,date,schoolClassId);
        try {
            ActivityDAO.addActivity(act);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dateActivity.setValue(null);
        txtDescriptionActivity.setText("");
        txtNameActivity.setText("");
    }
}
