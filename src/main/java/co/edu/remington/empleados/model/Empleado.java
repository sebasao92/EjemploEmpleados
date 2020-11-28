package co.edu.remington.empleados.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    private final String cedula;
    private final String nombre;
    private final Double salario;

    public Empleado(){
        this.cedula = "";
        this.nombre = "";
        this.salario = 0.0;
    }

    public Empleado(String cedula, String nombre, Double salario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.salario = salario;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
               "nombre='" + nombre + '\'' +
               ", salario=" + salario +
               '}';
    }
}
