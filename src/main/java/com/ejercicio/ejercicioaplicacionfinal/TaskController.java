package com.ejercicio.ejercicioaplicacionfinal;

import com.ejercicio.ejercicioaplicacionfinal.alerts.Alerts;
import com.ejercicio.ejercicioaplicacionfinal.dao.*;
import com.ejercicio.ejercicioaplicacionfinal.model.Category;
import com.ejercicio.ejercicioaplicacionfinal.model.Employee;
import com.ejercicio.ejercicioaplicacionfinal.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    @FXML
    private TextArea txtADescription;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDate;
    @FXML
    private ComboBox<Employee> cmbEmployee;
    @FXML
    private ComboBox<Category> cmbCategory;
    @FXML
    private ComboBox<Category> cmbCategoryFind;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnCleanFields;
    @FXML
    private ListView<String> lvCategory;

    @FXML
    protected void initialize(){
        cmbEmployee.getItems().clear();
        cmbCategory.getItems().clear();
        CategoryDao categoryDao = new CategoryDaoImpl();
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        try{
            //forma 1
            categoryDao.getCmbCategory(cmbCategory);
            cmbCategoryFind.setItems(cmbCategory.getItems());

            //forma 2
            List<Employee> employees = employeeDao.getAll();
            cmbEmployee.setItems(FXCollections.observableArrayList(employees));

        }catch (Exception e){
            Alerts.showAlert("Error", "Algo salio mal al intentar llenar los combobox", 3);
            System.out.println(e.getMessage());
        }

        cmbCategoryFind.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            try {
                if(cmbCategoryFind.getValue() == null) Alerts.showAlert("Error", "Selecciona un valor valido para la busqueda", 3);
                List<String> result =  employeeDao.getEmployeesByTaskCategory(cmbCategoryFind.getValue().getId());
                lvCategory.setItems(FXCollections.observableArrayList(result));
            }catch (Exception e){
                Alerts.showAlert("Error", "No se pudo traer la informacion de la categoria", 3);
            }
        });
    }
    @FXML
    protected void onBtnInsertClick() {
        if(txtADescription.getText().isBlank() || txtDate.getText().isBlank() || txtPrice.getText().isBlank()){
            Alerts.showAlert("Campos vacios", "Favor llenar todos los campos necesarios para ingresar la tarea", 2);
        }
        if(cmbEmployee.getValue() == null || cmbCategory.getValue() == null){
            Alerts.showAlert("Campos vacios", "Favor seleccionar valores de empleado y categoria", 2);
        }
        //yyyy-mm-dd
        Task task = new Task(txtADescription.getText(), LocalDate.parse(txtDate.getText()), Double.parseDouble(txtPrice.getText()), cmbEmployee.getValue(), cmbCategory.getValue());
        try {
            TaskDao taskDao = new TaskDaoImpl();
            taskDao.insertTask(task);
        }catch (Exception e){
            Alerts.showAlert("Error", "No se pudo insertar la tarea", 3);
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onBtnEmployeeClick(ActionEvent event) {
        //Cerrando actual
        Node source = (Node) event.getTarget();
        ((Stage) source.getScene().getWindow()).close();

        //Abriendo
        try{
            Stage stage = new Stage();
            HelloApplication app = new HelloApplication();
            app.start(stage);
        }catch (Exception e){
            System.out.println("No se pudo abrir la ventana de tareas");
        }
    }



}