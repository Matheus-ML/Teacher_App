module com.senai.teacherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.senai.teacherapp to javafx.fxml;
    exports com.senai.teacherapp;
    exports com.senai.teacherapp.controller;
    opens com.senai.teacherapp.controller to javafx.fxml;
}