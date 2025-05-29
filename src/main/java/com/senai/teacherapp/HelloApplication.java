package com.senai.teacherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/senai/teacherapp/views/hello-view.fxml"));
        Parent root = fxmlLoader.load(); //Condensa minha tela para o tamanho que coloquei no scene builder
        Scene scene = new Scene(root);
        stage.setTitle("TeacherApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}