package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.SchoolClassDAO;
import com.senai.teacherapp.Models.Notification;
import com.senai.teacherapp.Models.SchoolClass;
import com.senai.teacherapp.Models.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class RegisterSchoolClassController {

    @FXML private TableView<SchoolClass> registerTableView;
    @FXML private TableColumn<SchoolClass, Integer> tableID;
    @FXML private TableColumn<SchoolClass, String> tableName;
    @FXML private TableColumn<SchoolClass, Integer> tableStudents;
    @FXML private TextField txtRegisterNameClass;
    @FXML private TextField txtRegisterQtStudent;

    private UserSession session;
    public void setSession(UserSession session){
        this.session = session;
    }

    private Runnable onSchoolClassRegister;
    public void setOnSchoolClassRegister(Runnable callback) {
        this.onSchoolClassRegister = callback;
        loadActivities();
        loadActivitiesTable();
    }

    public void loadActivitiesTable(){
        tableID.setCellValueFactory(new PropertyValueFactory<>("idSchoolClass"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("nameSchoolClass"));
        tableStudents.setCellValueFactory(new PropertyValueFactory<>("quantityStudent"));
        adjustmentTable();
    }

    public void adjustmentTable() {
        tableID.setReorderable(false);
        tableID.setResizable(false);

        tableName.setReorderable(false);
        tableName.setResizable(false);

        tableStudents.setReorderable(false);
        tableStudents.setResizable(false);
    }

    public void loadActivities(){
        try{
            SchoolClassDAO scDAO = new SchoolClassDAO();
            ObservableList<SchoolClass> list = FXCollections.observableArrayList(scDAO.listSchoolClass(session.getId()));
            registerTableView.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnRegisterSchoolClass(ActionEvent event) {
        String nameClass = txtRegisterNameClass.getText();
        String qtStudent = txtRegisterQtStudent.getText();

        if(nameClass.isBlank() || qtStudent.isBlank()){
            new Notification().ErrorAlert("Campos vázios", "Por favor preencha todos os campos!");
            return;
        }
        try {
            if (Integer.parseInt(qtStudent) <= 0) {
                new Notification().ErrorAlert("Quantidade Inválida", "A quantidade de alunos precisa ser superior a 0!");
                return;
            }
        } catch (NumberFormatException e){
            new Notification().ErrorAlert("Valor inválido!", "A quantidade de alunos deve ser um número inteiro!");
            return;
        }

        SchoolClass sc = new SchoolClass(nameClass, Integer.parseInt(qtStudent));
        SchoolClassDAO scDAO = new SchoolClassDAO();
        try{
            scDAO.addSchoolClass(sc, session.getId());
            if (onSchoolClassRegister != null){
                onSchoolClassRegister.run();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtRegisterNameClass.setText("");
        txtRegisterQtStudent.setText("");
        loadActivities();
    }


}
