package com.senai.teacherapp.DAO;

import com.senai.teacherapp.Entity.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDAO {
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
    public static void listActivity() throws SQLException{
        String sql = "SELECT (nm_activity, ds_activity, dt_activity, wt_activity) FROM activity";
        try (Connection conn = ConnectDB.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while(rs.next()){

            }

            }catch(SQLException e){
            System.out.println("Erro ao listar" + e.getMessage());
        }
    }
}
