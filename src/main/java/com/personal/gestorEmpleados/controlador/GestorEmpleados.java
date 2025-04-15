package com.personal.gestorEmpleados.controlador;
import com.personal.gestorEmpleados.modelo.Empleados;
import com.personal.gestorEmpleados.controlador.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorEmpleados {
    private List<Empleados> empleados = new ArrayList<>();
    public void guardarEmpleado(Empleados empleado){
        String sql = "INSERT INTO empleados (nombre, puesto, salario) VALUES (?, ?, ?)";

        try(Connection conn = DatabaseConnection.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getPuesto());
            pstmt.setDouble(3, empleado.getSalario());

            pstmt.executeUpdate();
            System.out.println("Empleado guardado en a base de datos con éxito.");

        }catch (SQLException e){
            System.out.println("Error al guardar el empleado en la base de datos: " + e.getMessage());
        }

    }

    public void mostrarEmpleados(){
        String sql = "SELECT * FROM empleados";

        try(Connection conn = DatabaseConnection.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            boolean hayResultados = false;
            while (rs.next()){
                hayResultados = true;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String puesto = rs.getString("puesto");
                double salario = rs.getDouble("salario");

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Puesto: " + puesto);
                System.out.println("Salario: " + salario);
                System.out.println("--------------------");
            }

            if (!hayResultados){
                System.out.println("No hay empleados registrados en la base de datos.");
            }

        }catch (SQLException e){
            System.out.println("Error al mostrar los empleados: " + e.getMessage());
        }
    }

    public void buscarEmpleado(String nombre){
        String sql = "SELECT * FROM empleados WHERE LOWER(nombre) = LOWER(?)";

        try(Connection conn = DatabaseConnection.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String puesto = rs.getString("puesto");
                double salario = rs.getDouble("salario");
                System.out.println("Empleado encontrado: ");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Puesto: " + puesto);
                System.out.println("Salario: " + salario);
            }else{
                System.out.println("El empleado '" + nombre + "' no fue encontrado.");
            }
        } catch (SQLException e){
            System.out.println("Error al buscar el empleado: " + e.getMessage());
        }

    }

    public void calcularSalarioPromedio(){
        String sql = "SELECT AVG(salario) AS promedio FROM empleados";
        try(Connection conn = DatabaseConnection.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                double promedio = rs.getDouble("promedio");
                System.out.printf("El salrio promedio de los empleados es: $%.2f%n", promedio);
            } else{
                System.out.println("No hay empleados para calculare el promedio.");
            }
        }catch (SQLException e){
            System.out.println("Error al calcular el salario promedio: " + e.getMessage());
        }
    }

    public void actualizarEmpleado(String nombre, String nuevoPuesto, double nuevoSalario){
        String sql = "UPDATE empleados SET puesto = ?, salario = ? WHERE LOWER(nombre) = LOWER(?)";

        try(Connection conn = DatabaseConnection.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoPuesto);
            stmt.setDouble(2, nuevoSalario);
            stmt.setString(3, nombre);

            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0){
                System.out.println("Empleado actualizado con éxito.");
            } else {
                System.out.println("Empleado no encontrado.");
            }

        }catch (SQLException e){
            System.out.println("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(String nombre){
        String sql = "DELETE FROM empleados WHERE LOWER(nombre) = LOWER(?)";
        try(Connection conn = DatabaseConnection.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nombre);
            int filasEliminadas = stmt.executeUpdate();

            if(filasEliminadas > 0){
                System.out.println("Empleado eliminado con éxito.");
            }else {
                System.out.println("Error al eliminar el empleado: " + nombre);
            }
        }catch (SQLException e){
            System.out.println("Error al eliminar el empleado: " + e.getMessage());
        }
    }
}