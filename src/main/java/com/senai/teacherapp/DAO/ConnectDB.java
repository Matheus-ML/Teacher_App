package com.senai.teacherapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection connectDB() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/teacherapp","root","");
    }
}
