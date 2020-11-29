package co.edu.remington.empleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import co.edu.remington.empleados.exception.EmpleadoException;
import co.edu.remington.empleados.model.Empleado;
import co.edu.remington.empleados.service.EmpleadoService;

import static co.edu.remington.empleados.service.EmpleadoService.MENSAJE_EMPLEADO_NO_EXISTE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    private static String ID_EMPLEADO = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Test
    public void deberiaResponderConElEmpleadoSiTodoSaleBien() throws Exception {

        Empleado empleado = new Empleado("1", "Juan", 2000.0);
        when(empleadoService.obtenerEmpleado(ID_EMPLEADO))
                .thenReturn(empleado);

        mockMvc.perform(get("/empleado/" + ID_EMPLEADO))
               .andExpect(status().isFound())
               .andExpect(content().json(new ObjectMapper().writeValueAsString(empleado)));
    }

    @Test
    public void deberiaRetornar404SiNoExisteElEmpleado() throws Exception {

        when(empleadoService.obtenerEmpleado(ID_EMPLEADO))
                .thenThrow(
                        new EmpleadoException(HttpStatus.NOT_FOUND, MENSAJE_EMPLEADO_NO_EXISTE)
                );

        mockMvc.perform(get("/empleado/" + ID_EMPLEADO))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.errorMessage").value(MENSAJE_EMPLEADO_NO_EXISTE));
    }
}