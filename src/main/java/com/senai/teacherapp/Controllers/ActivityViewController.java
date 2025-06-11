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

    public ActivityViewController() {
    }

    private int schoolClassId;
    public void setSchoolClassId(int schoolClassId){
        this.schoolClassId = schoolClassId;
        loadActivities();
    }

    @FXML
    public void initialize(){
        loadTableAcitivities();
    }

    public void loadTableAcitivities(){
        tableActivityId.setCellValueFactory(new PropertyValueFactory<>("idActivity"));
        tableActivityName.setCellValueFactory(new PropertyValueFactory<>("nameActivity"));
        tableActivityDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionActivity"));
        tableActivityDate.setCellValueFactory(new PropertyValueFactory<>("dateActivity"));
        adjustmentTable();
    }

    public void adjustmentTable(){
        tableActivityId.setSortable(false);
        tableActivityId.setReorderable(false);
        tableActivityId.setResizable(false);

        tableActivityName.setSortable(false);
        tableActivityName.setReorderable(false);
        tableActivityName.setResizable(false);

        tableActivityDescription.setSortable(false);
        tableActivityDescription.setReorderable(false);
        tableActivityDescription.setResizable(false);

        tableActivityDate.setSortable(false);
        tableActivityDate.setReorderable(false);
        tableActivityDate.setResizable(false);
    }

    public void loadActivities(){
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senai/teacherapp/views/register-activity-view.fxml"));
        Parent root = loader.load();

        RegisterActivityController rac = loader.getController();
        rac.setSchoolClassId(this.schoolClassId);
        rac.setOnActivityRegistered(() -> loadActivities());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Cadastrar Atividade");
        stage.setScene(scene);
        stage.show();
    }

}
