package com.ejercicio.ejercicioaplicacionfinal.dao;

import com.ejercicio.ejercicioaplicacionfinal.alerts.Alerts;
import com.ejercicio.ejercicioaplicacionfinal.model.Category;
import com.ejercicio.ejercicioaplicacionfinal.model.DatabaseConnection;
import com.ejercicio.ejercicioaplicacionfinal.model.Employee;
import javafx.scene.control.ComboBox;

import java.sql.*;

public class CategoryDaoImpl implements CategoryDao{
    @Override
    public void getCmbCategory(ComboBox<Category> cmb) throws SQLException {
        Connection conn = DatabaseConnection.getConn();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, category FROM tbCategory");
        while (rs.next()){
            cmb.getItems().add(new Category(rs.getInt("id"), rs.getString("category")));
        }
        conn.close();
    }
}
