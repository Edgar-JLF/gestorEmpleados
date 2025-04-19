package com.personal.gestorEmpleados.dao;

import com.personal.gestorEmpleados.modelo.Empleado;
import com.personal.gestorEmpleados.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    @Override
    public void guardarEmpleado(Empleado empleado){
        String sql = "INSERT INTO empleados (nombre, puesto, salario) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseUtil.conectar())
        {
            if (conn != null){
                try(PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setString(1, empleado.getNombre());
                    stmt.setString(2, empleado.getPuesto());
                    stmt.setDouble(3, empleado.getSalario());
                    stmt.executeUpdate();

                    System.out.println("Empleado guardado con éxito.");
                }
            } else {
                System.out.println("Conexión fallida al guardar empleado.");
            }

        }catch(SQLException e){
            System.out.println("Error al guardar el empleado: " + e.getMessage());
        }
    }

    @Override
    public List<Empleado> obtenerTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try(Connection conn = DatabaseUtil.conectar()) {
            if (conn != null){
                try(PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        Empleado emp = new Empleado(
                                rs.getString("nombre"),
                                rs.getString("puesto"),
                                rs.getDouble("salario")
                        );
                        empleados.add(emp);
                    }
                }
            } else {
                System.out.println("Conexión fallida al listar empleados.");
            }

        }catch(SQLException e){
            System.out.println("Error al obtener empleados: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public Empleado buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM empleados WHERE nombre = ?";
        Empleado emp = null;
        try (Connection conn = DatabaseUtil.conectar()){
            if (conn != null){
                try( PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setString(1, nombre);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if(rs.next()){
                            emp = new Empleado(
                                    rs.getString("nombre"),
                                    rs.getString("puesto"),
                                    rs.getDouble("salario")
                            );
                        }
                    }
                }
            } else {
                System.out.println("Conexión fallida al buscar empleado.");
            }
        } catch (SQLException e){
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }

        return emp;
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleados SET puesto = ?, salario = ? WHERE nombre = ?";

        try(Connection conn = DatabaseUtil.conectar()){
            if (conn != null){
                try(PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setString(1, empleado.getPuesto());
                    stmt.setDouble(2, empleado.getSalario());
                    stmt.setString(3, empleado.getNombre());

                    int filas = stmt.executeUpdate();
                    if(filas > 0){
                        System.out.println("Empleado actualizado correctamente");
                    }else {
                        System.out.println("Empleado no encontrado");
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Error al actualizar empleado: " + e.getMessage());
        }
    }

    @Override
    public void eliminarEmpleado(String nombre) {
        String sql = "DELETE FROM empleados WHERE nombre = ?";

        try (Connection conn = DatabaseUtil.conectar()){
            if (conn != null){
                try(PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setString(1, nombre);
                    int filas = stmt.executeUpdate();

                    if(filas > 0){
                        System.out.println("Empleado eliminado correctamente.");
                    }else {
                        System.out.println("Empleado no encontrado.");
                    }
                }
            } else {
                System.out.println("Conexión fallida al eliminar empleado.");
            }

        } catch (SQLException e){
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    @Override
    public double calcularSalarioPromedio() {
        String sql = "SELECT AVG(salario) AS promedio FROM empleados";
        double promedio = 0;

        try(Connection conn = DatabaseUtil.conectar()){
            if (conn != null){
                try(PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
                    if (rs.next()){
                        promedio = rs.getDouble("promedio");
                    }
                }

            } else {
                System.out.println("Conexión fallida al calcular salario promedio.");
            }

        }catch (SQLException e) {
            System.out.println("Error al cualcular salario promedio: " + e.getMessage());
        }

        return promedio;
    }

}
