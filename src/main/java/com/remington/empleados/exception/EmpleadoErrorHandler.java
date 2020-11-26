package com.remington.empleados.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmpleadoErrorHandler {

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ErrorResponse> handleEmpleadoException(EmpleadoException ex){

        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(ex.getErrorMessage()));
    }
}
