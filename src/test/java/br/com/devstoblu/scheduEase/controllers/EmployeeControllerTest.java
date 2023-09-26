package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    EmployeeController employeeController;

    @Test
    public void addEmployee_shouldAddEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO(1L, "João da Silva", EmployeeRole.HairStylist, true);
        when(employeeService.addEmployee(employeeDTO)).thenReturn(1l);

        // Act
        ResponseEntity<Object> response = employeeController.addEmployee(employeeDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

    // Arrange
    // Act
    // Assert
}
