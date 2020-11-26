package com.remington.empleados.service;

import com.remington.empleados.exception.EmpleadoException;
import com.remington.empleados.model.Empleado;
import com.remington.empleados.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public HttpStatus crearEmpleado(Empleado empleado) {

        Optional<Empleado> empleadoBuscado = empleadoRepository.findById(empleado.getCedula());

        if(empleadoBuscado.isPresent()){
            throw new EmpleadoException(
                    HttpStatus.BAD_REQUEST, "El empleado ya existe en la base de datos"
            );
        } else {
            empleadoRepository.save(empleado);
            return HttpStatus.CREATED;
        }
    }

    public Iterable<Empleado> obtenerEmpleados(){

        return empleadoRepository.findAll();
    }

    public Empleado obtenerEmpleado(String cedula){

        Optional<Empleado> empleadoObtenido = empleadoRepository.findById(cedula);

        if(empleadoObtenido.isPresent()){

            return empleadoObtenido.get();
        } else {

            throw new EmpleadoException(
                    HttpStatus.NOT_FOUND, "El empleado no existe"
            );
        }
    }

    public HttpStatus eliminarEmpleado(String cedula) {

        try {
            empleadoRepository.deleteById(cedula);
        }catch (EmptyResultDataAccessException ex){
            throw new EmpleadoException(
                    HttpStatus.NOT_FOUND, "El empleado a eliminar no existe"
            );
        }

        return HttpStatus.OK;
    }
}
