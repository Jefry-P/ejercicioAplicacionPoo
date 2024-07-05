package com.ejercicio.ejercicioaplicacionfinal.dao;

import com.ejercicio.ejercicioaplicacionfinal.alerts.Alerts;
import com.ejercicio.ejercicioaplicacionfinal.model.Category;
import com.ejercicio.ejercicioaplicacionfinal.model.DatabaseConnection;
import com.ejercicio.ejercicioaplicacionfinal.model.Task;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao{
    public void insertTask(Task task) throws SQLException{
        Connection conn = DatabaseConnection.getConn();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO tbTask(description, price, taskDate, idEmployee, idCategory) VALUES(?, ?, ?, ?, ?)");
        ps.setString(1, task.getDescription());
        ps.setDouble(2, task.getPrice());
        ps.setDate(3, Date.valueOf(task.getTaskDate()));
        ps.setInt(4, task.getEmployee().getId());
        ps.setInt(5, task.getCategory().getId());
        int res = ps.executeUpdate();
        conn.close();
        if (res > 0) Alerts.showAlert("Exito", "Tarea ingresada satisfactoriamente", 1);
        else throw new SQLException();
    }

    @Override
    public List<String> getTasksByEmployee(int idEmployee, Date start, Date end) throws SQLException {
        Connection conn = DatabaseConnection.getConn();
        PreparedStatement ps = conn.prepareStatement("Select t.id, t.description, t.taskDate from tbTask t inner join tbEmployee e on t.idEmployee = e.id where t.taskDate between ? and ? and e.id = ?");
        ps.setDate(1, start);
        ps.setDate(2, end);
        ps.setInt(3, idEmployee);

        ResultSet rs = ps.executeQuery();
        List<String> result = new ArrayList<>();
        while(rs.next()){
            result.add(rs.getInt("id") + ". " + rs.getString("description") + ", fecha: " + rs.getDate("taskDate"));
        }
        conn.close();
        return result;
    }
}
