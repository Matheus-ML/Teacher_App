package com.senai.teacherapp.controller;

import com.senai.teacherapp.Entity.SchoolClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

//Classe da tela principal do professor.
public class PrincipalViewController {
    @FXML
    private Button btnExit;

    @FXML
    private TableColumn<SchoolClass, Void> tableAction;

    @FXML
    private TableColumn<SchoolClass, Integer> tableID;

    @FXML
    private TableView<SchoolClass> tableListStudent;

    @FXML
    private TableColumn<SchoolClass, String> tableName;


}
