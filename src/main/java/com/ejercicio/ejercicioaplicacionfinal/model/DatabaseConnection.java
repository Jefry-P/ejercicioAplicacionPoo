package com.ejercicio.ejercicioaplicacionfinal.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/dbTaller";
    private static final String user = "jef";
    private static final String password = "root";
    private DatabaseConnection(){
    }

    public static Connection getConn() {
        try{
            return DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            System.out.println("Algo salió mal al intentar conectar con la base de datos");
        }
        return null;
    }


}
