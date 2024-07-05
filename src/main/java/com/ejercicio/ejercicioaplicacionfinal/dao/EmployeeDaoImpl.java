package com.ejercicio.ejercicioaplicacionfinal.dao;

import com.ejercicio.ejercicioaplicacionfinal.alerts.Alerts;
import com.ejercicio.ejercicioaplicacionfinal.model.DatabaseConnection;
import com.ejercicio.ejercicioaplicacionfinal.model.Employee;
import com.ejercicio.ejercicioaplicacionfinal.model.Task;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        Connection conn = DatabaseConnection.getConn();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO tbEmployee(name, lastname) VALUES(?,?)");
        ps.setString(1, employee.getName());
        ps.setString(2, employee.getLastName());
        int result = ps.executeUpdate();
        conn.close();
        if (result > 0) Alerts.showAlert("Exito", "Empleado ingresado satisfactoriamente", 1);
        else throw new SQLException();
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = DatabaseConnection.getConn();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, name, lastname from tbEmployee");
        while (rs.next()){
            employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("lastname")));
        }
        conn.close();
        return employees;
    }

    @Override
    public List<String> getEmployeesByTaskCategory(int idCategory) throws SQLException {
        Connection conn = DatabaseConnection.getConn();
        PreparedStatement ps = conn.prepareStatement("SELECT e.id, e.name, e.lastname, count(t.idEmployee) as tasks FROM tbEmployee e INNER JOIN tbTask t on t.idEmployee = e.id INNER JOIN tbCategory c on t.idCategory = c.id where c.id = ? group by e.id, e.name, e.lastname order by e.lastname;");

        ps.setInt(1, idCategory);
        ResultSet rs = ps.executeQuery();
        List<String> result = new ArrayList<>();
        while(rs.next()){
            result.add(rs.getInt("id") + ". " + rs.getString("name") + " " + rs.getString("lastname") + ", cantidad de tareas: " + rs.getString("tasks"));
        }
        conn.close();
        return result;
    }
}
