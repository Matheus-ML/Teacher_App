package com.senai.teacherapp.DAO;

import com.senai.teacherapp.Models.Login;
import com.senai.teacherapp.Models.UserSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public UserSession getLogin(Login login) throws SQLException {
            String sql = "SELECT * FROM login WHERE id_user = ? AND id_password = ?";

            try(Connection conn = ConnectDB.connectDB();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, login.getUser());
                ps.setString(2, login.getPassword());

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        int id = rs.getInt("cd_login");
                        String name = rs.getString("id_nameProfessor");
                        return new UserSession(id, name);
                    }

                }
            }
            return null;
    }
}
