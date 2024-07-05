package com.ejercicio.ejercicioaplicacionfinal.dao;

import com.ejercicio.ejercicioaplicacionfinal.model.Category;
import com.ejercicio.ejercicioaplicacionfinal.model.Employee;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;

public interface CategoryDao {
    void getCmbCategory(ComboBox<Category> cmb) throws SQLException;
}
