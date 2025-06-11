package com.senai.teacherapp.DAO;

import com.senai.teacherapp.Models.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    //Método que adiciona um objeto do tipo Activity ao banco de dados
    public static void addActivity(Activity act) throws SQLException{
        String sql = "INSERT INTO activity (nm_activity, ds_activity, dt_activity, cd_schoolclass) VALUES (?,?,?,?)";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, act.getNameActivity());
            ps.setString(2, act.getDescriptionActivity());
            ps.setObject(3, act.getDateActivity());
            ps.setInt(4, act.getIdClass());
            ps.executeUpdate();
        }
    }

    //Método que lista as atividades na tela principal do professor
    public List<Activity> listViewActivity(int idClass) throws SQLException {
        List<Activity> activities = new ArrayList<>();
        String sql = "SELECT * FROM activity WHERE cd_schoolclass = ?";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idClass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activity a = new Activity();
                a.setIdActivity(rs.getInt("cd_activity"));
                a.setNameActivity(rs.getString("nm_activity"));
                a.setDescriptionActivity(rs.getString("ds_activity"));
                a.setDateActivity(rs.getObject("dt_activity", LocalDate.class));
                a.setIdClass(idClass);
                activities.add(a);
            }
        }
        return activities;
    }

    public void deleteActivity(int cd_activity) throws SQLException {
        String sql = "DELETE FROM activity WHERE cd_activity = ?";
        try(Connection conn = ConnectDB.connectDB();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cd_activity);
            ps.executeUpdate();
        }
    }

    public boolean hasActivities(int classId) throws SQLException {
        String query = "SELECT COUNT(*) FROM activity WHERE cd_schoolclass = ?";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, classId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

}

