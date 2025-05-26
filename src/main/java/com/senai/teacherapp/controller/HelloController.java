package com.senai.teacherapp.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    void btnEnter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/senai/teacherapp/principal-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela Principal");
        stage.setScene(scene);
        stage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        /* Stage = disparo, node= prenda-o, source = identifique de onde veio, scene de que cena veio, window e close =  pegue essa janele e feche*/
    }

    @FXML
    private PasswordField txtPassoword;

    @FXML
    private TextField txtUser;
}