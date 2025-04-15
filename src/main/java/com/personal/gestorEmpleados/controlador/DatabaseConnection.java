package com.personal.gestorEmpleados.controlador;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestor_empleados";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "toor";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS empleados (" +
                "id SERIAL PRIMARY KEY," +
                "nombre VARCHAR(50) NOT NULL," +
                "puesto VARCHAR(50) NOT NULL," +
                "salario DECIMAL(10, 2) NOT NULL)";

        try (Connection conn = conectar()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(sql);
                    System.out.println("Tabla 'empleados' verificada/creada.");
                }
            } else {
                System.out.println("No se pudo crear la tabla porque la conexión es nula.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}
