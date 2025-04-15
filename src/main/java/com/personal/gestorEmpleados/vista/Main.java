package com.personal.gestorEmpleados.vista;

import com.personal.gestorEmpleados.controlador.DatabaseConnection;
import com.personal.gestorEmpleados.controlador.GestorEmpleados;
import com.personal.gestorEmpleados.modelo.Empleados;
import java.util.Scanner;

public class Main {
    private static Empleados capturarEmpleado(Scanner sc){
        System.out.println("Ingrese el nombre del empleado: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el puesto del empleado: ");
        String puesto = sc.nextLine();

        // Validar que el salario sea un número
        double salario;
        while (true) {
            try {
                System.out.println("Ingrese el salario del empleado: ");
                salario = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido para el salario.");
            }
        }

        return new Empleados(nombre, puesto, salario);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorEmpleados empleados = new GestorEmpleados();
        boolean continuar = true;

        // Crear la tabla si no existe
        DatabaseConnection.crearTabla();

        while (continuar) {
            System.out.println("Bienvenido al sistema de gestion de empleados");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Guardar empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Buscar empleado");
            System.out.println("4. Calcular salario promedio");
            System.out.println("5. Actualizar empleado");
            System.out.println("6. Eliminar empleado");
            System.out.println("7. Salir");

            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido para la opción.");
                continue;
            }

            switch (opcion) {
                case 1:
                    empleados.guardarEmpleado(capturarEmpleado(sc));
                    break;

                case 2:
                    empleados.mostrarEmpleados();
                    break;

                case 3:
                    System.out.println("Ingrese el nombre del empleado a buscar: ");
                    empleados.buscarEmpleado(sc.nextLine());
                    break;

                case 4:
                    empleados.calcularSalarioPromedio();
                    break;

                case 5:
                    System.out.println("Ingrese el nombre del empleado a actualizar: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el nuevo puesto del empleado: ");
                    String nuevoPuesto = sc.nextLine();

                    double nuevoSalario;
                    while (true) {
                        try {
                            System.out.println("Ingrese el nuevo salario del empleado: ");
                            nuevoSalario = Double.parseDouble(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Ingrese un número válido para el salario.");
                        }
                    }
                    empleados.actualizarEmpleado(nombre, nuevoPuesto, nuevoSalario);
                    break;

                case 6:
                    System.out.println("Ingrese el nombre del empleado a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    empleados.eliminarEmpleado(nombreEliminar);
                    break;

                case 7:
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                    break;
            }
        }

        sc.close();
    }
}
