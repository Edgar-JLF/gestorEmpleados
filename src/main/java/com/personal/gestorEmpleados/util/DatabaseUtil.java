package com.personal.gestorEmpleados.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestor_empleados"; //Controlador de la base de datos junto con la base de datos
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "toor";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        }catch (SQLException e){
               System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
