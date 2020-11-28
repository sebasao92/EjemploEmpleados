package co.edu.remington.empleados.exception;

import org.springframework.http.HttpStatus;

public class EmpleadoException extends RuntimeException {

    private final HttpStatus status;
    private final String errorMessage;

    public EmpleadoException(HttpStatus status, String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
