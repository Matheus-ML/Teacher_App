package com.senai.teacherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("/com/senai/teacherapp/views/login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("TeacherApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }


}