package com.personal.gestorEmpleados.controlador;
import com.personal.gestorEmpleados.modelo.Empleados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorEmpleados {
    private List<Empleados> empleados = new ArrayList<>();
    public void guardarEmpleado(Empleados empleado){
        empleados.add(empleado);
        System.out.println(String.format("El empleado %s, fue guardado con éxito", empleado.getNombre()));
    }

    public void mostrarEmpleados(){
        if(empleados.isEmpty()){
            System.out.println("No hay empleados registrados.");
            return;
        }

        for (Empleados empleado : empleados) {
            System.out.println(empleado);
            System.out.println("--------------------");
        }

    }

    public void buscarEmpleado(String nombre){
        for (Empleados empleado : empleados){
            if(empleado.getNombre().equalsIgnoreCase(nombre)){ //Verifica si el nombre del empleado es igual al nombre ingresado
                System.out.println(empleado);
                return;
            }
        }
        System.out.println(String.format("El empleado %s no encontrado. ", nombre));

    }

    public double calcularSalarioPromedio(){
        if (empleados.isEmpty()) return 0;
        double total = 0;
        for(Empleados empleado : empleados){
            total += empleado.getSalario();
        }
        return total / empleados.size();
    }

    public void actualizarEmpleado(String nombre, String nuevoPuesto, double nuevoSalario){
        for (Empleados empleado: empleados){
            if(empleado.getNombre().equalsIgnoreCase(nombre)){
                empleado.setPuesto(nuevoPuesto);
                empleado.setSalario(nuevoSalario);
                System.out.println("Empleado actualizado con éxito.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    public void eliminarEmpleado(String nombre){
        Iterator<Empleados> iterator = empleados.iterator();
        while(iterator.hasNext()){
            Empleados empleado = iterator.next();
            if(empleado.getNombre().equalsIgnoreCase(nombre)){
                iterator.remove();
                System.out.println("Empleado eliminado con éxito.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }
}