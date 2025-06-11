package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.ActivityDAO;
import com.senai.teacherapp.Models.Activity;
import com.senai.teacherapp.Models.Notification;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterActivityController {
    @FXML
    private DatePicker dateActivity;
    @FXML
    private TextArea txtDescriptionActivity;
    @FXML
    private TextField txtNameActivity;
    private Runnable onActivityRegistered;
    private int schoolClassId;

    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public void setOnActivityRegistered(Runnable callback) {
        this.onActivityRegistered = callback;
    }

    @FXML
    void btnConfirmActivity() {
        LocalDate date = dateActivity.getValue();
        String description = txtDescriptionActivity.getText();
        String name = txtNameActivity.getText();
        if (description.isEmpty() || name.isEmpty() || dateActivity.getValue() == null) {
            new Notification().ErrorAlert("Campos vázios", "Por favor preencha todos os campos, com informações válidas!");
            return;
        }

        if(date.isBefore(LocalDate.now())){
            new Notification().ErrorAlert("Data inválida! ", "A data de entrega não pode ser anterior à hoje!");
            return;
        }

        Activity act = new Activity(name, description, date, schoolClassId);
        try {
            ActivityDAO.addActivity(act);
            if (onActivityRegistered != null) {
                onActivityRegistered.run();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dateActivity.setValue(null);
        txtDescriptionActivity.setText("");
        txtNameActivity.setText("");

    }
}
