package com.ejercicio.ejercicioaplicacionfinal.alerts;

import javafx.scene.control.Alert;

public class Alerts {
    public static void showAlert(String title, String content, int type){
        Alert alert = null;
        switch (type){
            case 1:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 2:
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            case 3:
                alert = new Alert(Alert.AlertType.ERROR);
                break;
        }
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
