package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.SchoolClassDAO;
import com.senai.teacherapp.Models.SchoolClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;

//Classe da tela principal do professor.
public class PrincipalViewController {
    @FXML
    void btnExit(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private TableView<SchoolClass> tableListStudent;

    @FXML
    private TableColumn<SchoolClass, Integer> tableID;

    @FXML
    private TableColumn<SchoolClass, String> tableName;

    @FXML
    private Button btnDeleteTablePV;

    @FXML
    private Button listTablePV;

    @FXML
    public void initialize(){
        tableID.setCellValueFactory(new PropertyValueFactory<>("idSchoolClass"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("nameSchoolClass"));

        try{
            SchoolClassDAO dao = new SchoolClassDAO();
            ObservableList<SchoolClass> list = FXCollections.observableArrayList(dao.listSchoolClass());
            tableListStudent.setItems(list);
        } catch (SQLException e){
            System.out.println("Deu zebra na lista: " + e);
        }
    }

    @FXML
    private Text txtNameProfessor;
}
