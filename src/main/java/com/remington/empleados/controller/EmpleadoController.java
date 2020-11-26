package com.remington.empleados.controller;

import com.remington.empleados.model.Empleado;
import com.remington.empleados.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/empleados")
    public ResponseEntity<Iterable<Empleado>> obtenerEmpleados(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        empleadoService.obtenerEmpleados()
                );
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(
            @PathVariable("id") String cedula
    ){

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        empleadoService.obtenerEmpleado(cedula)
                );
    }

    @PostMapping("/empleado")
    public ResponseEntity<Void> crearEmpleado(
            @RequestBody Empleado empleado){

        return ResponseEntity
                .status(
                        empleadoService.crearEmpleado(empleado)
                )
                .build();
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<Map<String, String>> eliminarEmpleado(
            @PathVariable("id") String cedula
    ){

        Map<String, String> map = new HashMap<>();
        map.put("message", "El empleado ha sido eliminado");

        return ResponseEntity
                .status(
                        empleadoService.eliminarEmpleado(cedula)
                )
                .body(map);
    }

    @PutMapping("/empleado")
    public String actualizarEmpleado(){

        //TODO
        return "ActualizarEmpleado";
    }

    @GetMapping("/empleados/mejores")
    public String obtenerMejoresEmpleados(){

        //TODO
        return "MejoresEmpleados";
    }
}
