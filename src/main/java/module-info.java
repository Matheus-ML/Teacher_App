module com.senai.teacherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.senai.teacherapp.Models to javafx.base;
    opens com.senai.teacherapp to javafx.fxml;
    exports com.senai.teacherapp;
    exports com.senai.teacherapp.Controllers;
    opens com.senai.teacherapp.Controllers to javafx.fxml;
}