package co.edu.remington.empleados.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.remington.empleados.model.Empleado;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, String> {
}
