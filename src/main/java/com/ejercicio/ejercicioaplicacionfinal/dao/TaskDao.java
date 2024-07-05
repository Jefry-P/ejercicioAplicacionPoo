package com.ejercicio.ejercicioaplicacionfinal.dao;

import com.ejercicio.ejercicioaplicacionfinal.model.Category;
import com.ejercicio.ejercicioaplicacionfinal.model.Employee;
import com.ejercicio.ejercicioaplicacionfinal.model.Task;
import javafx.scene.control.ComboBox;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface TaskDao {
    void insertTask(Task task) throws SQLException;

    List<String> getTasksByEmployee(int idEmployee, Date start, Date end) throws SQLException;
}
