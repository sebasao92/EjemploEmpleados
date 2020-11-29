package co.edu.remington.empleados.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import co.edu.remington.empleados.exception.EmpleadoException;
import co.edu.remington.empleados.model.Empleado;
import co.edu.remington.empleados.repository.EmpleadoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    private static String ID_EMPLEADO = "1";

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    public void deberiaRetornarUnErrorCuandoElEmpleadoNoExiste(){

        when(empleadoRepository.findById(ID_EMPLEADO))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(EmpleadoException.class,
                     () -> empleadoService.obtenerEmpleado(ID_EMPLEADO)
        );
    }

    @Test
    public void deberiaRetornarEmpleadoSiTodoSaleBien() {

        Empleado empleado = new Empleado("1", "Juan", 2000.0);
        when(empleadoRepository.findById(ID_EMPLEADO))
                .thenReturn(Optional.of(empleado));

        Empleado empleadoObtenido = empleadoService.obtenerEmpleado(ID_EMPLEADO);

        assertEquals(empleado, empleadoObtenido);
    }
}