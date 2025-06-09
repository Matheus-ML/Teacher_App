package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.LoginDAO;
import com.senai.teacherapp.Models.Login;
import com.senai.teacherapp.Models.Notification;
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
import java.sql.SQLException;

public class LoginController {
    @FXML private PasswordField txtPassoword;

    @FXML private TextField txtUser;

    @FXML
    void btnEnter(ActionEvent event) throws IOException {
        Login login = new Login(txtUser.getText(), txtPassoword.getText());
        try {
            String professorName = new LoginDAO().getLogin(login);
            if (professorName != null) {
                openPrincipalView(event, professorName);
            } else {
                new Notification().ErrorAlert("Erro", "Dados Incorretos! Preencha novamente.");
            }
        } catch (SQLException e) {
            System.out.println("Problema ao acessar o banco de dados." + e);
        }
    }

    private void openPrincipalView(ActionEvent event, String professorName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/senai/teacherapp/views/principal-view.fxml"));

        Parent root = fxmlLoader.load();

        PrincipalViewController pvc = fxmlLoader.getController();
        pvc.setProfessorName(professorName);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Tela Principal");
        stage.setScene(scene);
        stage.show();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }


}