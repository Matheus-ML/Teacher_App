package com.senai.teacherapp.DAO;

import com.senai.teacherapp.Models.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    //Método que adiciona um objeto do tipo Activity ao banco de dados
    public static void addActivity(Activity act) {
        String sql = "INSERT INTO activity (nm_activity, ds_activity, dt_activity, wt_activity) VALUES (?,?,?,?)";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, act.getNameActivity());
            ps.setString(2, act.getDescriptionActivity());
            ps.setObject(3, act.getDateActivity());
            ps.setDouble(4, act.getWeightActivity());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir activity" + e.getMessage());
        }
    }

    //Método que lista as atividades na tela principal do professor
    public static List<Activity> listPrincipalViewActivity() throws SQLException {
        List<Activity> activities = new ArrayList<>();
        String sql = "SELECT cd_activity, nm_activity FROM activity";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Activity a = new Activity();
                a.setIdActivity(rs.getInt("cd_activity"));
                a.setNameActivity(rs.getString("nm_activity"));
                activities.add(a);
            }
        }
        return activities;
    }


}

