module com.ejercicio.ejercicioaplicacionfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ejercicio.ejercicioaplicacionfinal to javafx.fxml;
    exports com.ejercicio.ejercicioaplicacionfinal;
}