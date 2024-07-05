package com.ejercicio.ejercicioaplicacionfinal;

import com.ejercicio.ejercicioaplicacionfinal.alerts.Alerts;
import com.ejercicio.ejercicioaplicacionfinal.dao.EmployeeDao;
import com.ejercicio.ejercicioaplicacionfinal.dao.EmployeeDaoImpl;
import com.ejercicio.ejercicioaplicacionfinal.dao.TaskDao;
import com.ejercicio.ejercicioaplicacionfinal.dao.TaskDaoImpl;
import com.ejercicio.ejercicioaplicacionfinal.model.DatabaseConnection;
import com.ejercicio.ejercicioaplicacionfinal.model.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.sql.Date;

public class HelloController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtIdEmployee;
    @FXML
    private TextField txtDate1;
    @FXML
    private TextField txtDate2;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnCleanFields;
    @FXML
    private ListView<String> lvTask;

    @FXML
    protected void onBtnInsertClick() {
        if(txtName.getText().isBlank() || txtLastname.getText().isBlank()){
            Alerts.showAlert("Campos vacios", "Favor llenar todos los campos necesarios para ingresar el usuario", 2);
        }
        Employee employee = new Employee(txtName.getText(), txtLastname.getText());
        try {
            EmployeeDao employeeDao = new EmployeeDaoImpl();
            employeeDao.insertEmployee(employee);
        }catch (Exception e){
            Alerts.showAlert("Error", "No se pudo insertar el empleado", 3);
        }
    }
    @FXML
    protected void onBtnConsultarClick() {
        if(txtIdEmployee.getText().isBlank() || txtDate1.getText().isBlank() || txtDate2.getText().isBlank() ){
            Alerts.showAlert("Campos vacios", "Favor llenar todos los campos necesarios para consultar tareas", 2);
        }
        try {
            TaskDao taskDao = new TaskDaoImpl();
            lvTask.setItems(FXCollections.observableArrayList(taskDao.getTasksByEmployee(Integer.parseInt(txtIdEmployee.getText()), Date.valueOf(txtDate1.getText()), Date.valueOf(txtDate2.getText()))));
        }catch (Exception e){
            Alerts.showAlert("Error", "No se pudo insertar el empleado", 3);
        }
    }

    @FXML
    protected void onBtnTaskClick(ActionEvent event) {
        //Cerrando actual
        Node source = (Node) event.getTarget();
        ((Stage) source.getScene().getWindow()).close();

        //Abriendo
        try{
            Stage stage = new Stage();
            TaskApplication app = new TaskApplication();
            app.start(stage);
        }catch (Exception e){
            System.out.println("No se pudo abrir la ventana de tareas");
        }
    }
}