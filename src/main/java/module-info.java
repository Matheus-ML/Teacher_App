module com.senai.teacherapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senai.teacherapp to javafx.fxml;
    exports com.senai.teacherapp;
}