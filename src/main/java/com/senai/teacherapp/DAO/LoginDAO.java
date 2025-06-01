package com.senai.teacherapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean getLogin(String user, String password) throws SQLException {
            String sql = "SELECT * FROM login WHERE id_user = ? AND id_password = ?";

            try(Connection conn = ConnectDB.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, user);
                ps.setString(2, password);

                try(ResultSet rs = ps.executeQuery()){
                    return rs.next();
                }
            }
    }

    public static void main(String[] args) {
        try{
            if(new LoginDAO().getLogin("karize", "karize123")){
                System.out.println("Deu certo");
            } else {
                System.out.println("deu bum bah gurizada");
            }

        } catch (SQLException e) {
            System.out.println("bah guri" + e.getMessage());
        }

    }
}
