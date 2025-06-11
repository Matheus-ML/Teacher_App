package com.senai.teacherapp.DAO;

import com.senai.teacherapp.Models.Notification;
import com.senai.teacherapp.Models.SchoolClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolClassDAO {
    public void addSchoolClass(SchoolClass schoolClass, int professorId) throws SQLException {
        String sql = "INSERT INTO schoolclass (nm_schoolclass, qt_student, cd_login) VALUES (?,?,?)";

        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, schoolClass.getNameSchoolClass());
            if (schoolClass.getQuantityStudent()>0) {
                ps.setInt(2, schoolClass.getQuantityStudent());
                ps.setInt(3, professorId);
                ps.executeUpdate();
            } else {
                new Notification().ErrorAlert("Erro", "A quantidade de alunos deve ser maior que 0");
            }
        }
    }

    public List<SchoolClass> listSchoolClass(int professorId) throws SQLException {
        List<SchoolClass> schoolClasses = new ArrayList<>();
        String sql = "SELECT cd_schoolclass, nm_schoolclass, qt_student FROM schoolclass WHERE cd_login = ?";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, professorId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SchoolClass scs = new SchoolClass(
                        rs.getInt("cd_schoolclass"),
                        rs.getString("nm_schoolclass"),
                        rs.getInt("qt_student"));

                schoolClasses.add(scs);
            }
        }
        return schoolClasses;
    }

    public void deleteSchoolClass(int cdSchoolClas) throws SQLException {
        String sql = "DELETE FROM schoolclass WHERE cd_schoolclass = ?";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cdSchoolClas);
            ps.executeUpdate();
        }
    }
}

