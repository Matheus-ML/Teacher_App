package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.SchoolClassDAO;
import com.senai.teacherapp.Models.Notification;
import com.senai.teacherapp.Models.SchoolClass;
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

    @FXML
    public void initialize(){
        tableID.setCellValueFactory(new PropertyValueFactory<>("idSchoolClass"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("nameSchoolClass"));
        tableStudents.setCellValueFactory(new PropertyValueFactory<>("quantityStudent"));

        try{
            SchoolClassDAO scDAO = new SchoolClassDAO();
            ObservableList<SchoolClass> list = FXCollections.observableArrayList(scDAO.listSchoolClass());
            registerTableView.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private TableView<SchoolClass> registerTableView;
    @FXML private TableColumn<SchoolClass, Integer> tableID;
    @FXML private TableColumn<SchoolClass, String> tableName;
    @FXML private TableColumn<SchoolClass, Integer> tableStudents;
    @FXML private TextField txtRegisterNameClass;
    @FXML private TextField txtRegisterQtStudent;

    @FXML
    void btnRegisterSchoolClass(ActionEvent event) {
        String nameClass = txtRegisterNameClass.getText();
        String qtStudent = txtRegisterQtStudent.getText();

        if(nameClass.isBlank() || qtStudent.isBlank()){
            new Notification().ErrorAlert("Campos vázios", "Por favor preencha todos os campos!");
            return;
        }

        if(Integer.parseInt(qtStudent) <= 0){
            new Notification().ErrorAlert("Quantidade Inválida", "A quantidade de alunos precisa ser superior a 0!");
            return;
        }

        SchoolClass sc = new SchoolClass(nameClass, Integer.parseInt(qtStudent));
        SchoolClassDAO scDAO = new SchoolClassDAO();
        try{
            scDAO.addSchoolClass(sc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sc.getNameSchoolClass());
        txtRegisterNameClass.setText("");
        txtRegisterQtStudent.setText("");
        initialize();
    }


}
