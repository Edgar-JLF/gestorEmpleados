package com.personal.gestorEmpleados.modelo;

public class Empleado {
    private String nombre;
    private String puesto;
    private double salario;

    public Empleado(String nombre, String puesto, double salario){
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getNombre(){
        return nombre;
    }
    public String getPuesto(){
        return puesto;
    }
    public double getSalario(){
        return salario;
    }

    public void setPuesto(String nuevoPuesto){
        this.puesto = nuevoPuesto;
    }

    public void setSalario(double nuevoSalario){
        if(nuevoSalario > 0){
            this.salario = nuevoSalario;
        } else {
            System.out.println("Error: El salario debe ser mayor a 0.");
        }
    }

    @Override
    public String toString(){
        return String.format("Nombre: %s | Puesto: %s | Salario: %.2f", nombre, puesto, salario);
    }
}