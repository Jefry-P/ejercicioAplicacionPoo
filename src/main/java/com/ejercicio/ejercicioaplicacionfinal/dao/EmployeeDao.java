package com.ejercicio.ejercicioaplicacionfinal.dao;

import com.ejercicio.ejercicioaplicacionfinal.model.Employee;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeDao {
    void insertEmployee(Employee employee) throws SQLException;
    List<Employee> getAll() throws SQLException;
    List<String> getEmployeesByTaskCategory(int idCategory) throws SQLException;
}
