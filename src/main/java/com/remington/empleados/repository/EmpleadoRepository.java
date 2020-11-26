package com.remington.empleados.repository;

import com.remington.empleados.model.Empleado;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, String> {
}
