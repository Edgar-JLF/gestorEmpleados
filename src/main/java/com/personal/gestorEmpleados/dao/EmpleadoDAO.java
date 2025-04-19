package com.personal.gestorEmpleados.dao;

import com.personal.gestorEmpleados.modelo.Empleado;
import java.util.List;


public interface EmpleadoDAO {
    void guardarEmpleado(Empleado empleado);
    List<Empleado> obtenerTodos();
    Empleado buscarPorNombre(String nombre);
    void actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(String nombre);
    double calcularSalarioPromedio();
}
