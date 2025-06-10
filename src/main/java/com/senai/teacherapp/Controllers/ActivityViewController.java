package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.ActivityDAO;
import com.senai.teacherapp.Models.Activity;
import com.senai.teacherapp.Models.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ActivityViewController {
    @FXML private TableColumn<Activity, LocalDate> tableActivityDate;
    @FXML private TableColumn<Activity, String> tableActivityDescription;
    @FXML private TableColumn<Activity, Integer> tableActivityId;
    @FXML private TableColumn<Activity, String> tableActivityName;
    @FXML private TableView<Activity> tableActivity;

    private int schoolClassId;

    public ActivityViewController() {
    }

    public void setSchoolClassId(int schoolClassId){
        this.schoolClassId = schoolClassId;
        loadActivities();
    }

    @FXML
    public void initialize(){
        tableActivityId.setCellValueFactory(new PropertyValueFactory<>("idActivity"));
        tableActivityName.setCellValueFactory(new PropertyValueFactory<>("nameActivity"));
        tableActivityDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionActivity"));
        tableActivityDate.setCellValueFactory(new PropertyValueFactory<>("dateActivity"));

    }

    public void loadActivities(){
        System.out.println(schoolClassId);
        try {
            ActivityDAO acDAO = new ActivityDAO();
            ObservableList<Activity> activities = FXCollections.observableArrayList(acDAO.listViewActivity(schoolClassId));
            tableActivity.setItems(activities);
        } catch (SQLException e) {
            e.printStackTrace();
            new Notification().ErrorAlert("Erro ao carregar atividades", "Erro: " + e);
        }
    }

    @FXML
    void btnOnRegisterActivity(ActionEvent event) throws IOException {
        System.out.println(schoolClassId);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senai/teacherapp/views/register-activity-view.fxml"));
        Parent root = loader.load();

        RegisterActivityController rac = loader.getController();
        rac.setSchoolClassId(this.schoolClassId);


        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Atividade");
        stage.setScene(scene);
        stage.show();
    }

}
