package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeNameDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.repositories.EmployeeRepository;
import br.com.devstoblu.scheduEase.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;
    @Mock
    EmployeeRepository repository;
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

    @Test
    public void deleteEmployee_shouldDeleteEmployee() {
        // Arrange
        Long id = 1l;
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        doAnswer((i) -> {
            employees.remove(employee1);
            return true;
        }).when(employeeService).deleteEmployee(id);

        // Act
        employeeController.deleteEmployee(id);

        // Assert
        Assert.assertEquals(employees.size(), 1);
    }

    @Test
    public void updateEmployee_shouldUpdateEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1l);
        when(employeeService.searchAnEmployeeById(employeeDTO.getId())).thenReturn(employeeDTO);

        // Act
        ResponseEntity<Object> response = employeeController.updateEmployee(employeeDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listEmployees_shouldListEmployees() {
        // Arrange
        List<EmployeeDTO> employeeDTOList = List.of(new EmployeeDTO(), new EmployeeDTO());
        when(employeeService.listEmployees()).thenReturn(employeeDTOList);

        // Act
        List<EmployeeDTO> employeeDTOSListed = employeeController.listEmployees();

        // Assert
        assertEquals(employeeDTOList, employeeDTOSListed);
    }

    @Test
    public void searchAnEmployee_shouldSearchAnEmployee() throws Exception {
        // Arrange
        EmployeeNameDTO employeeNameDTO = new EmployeeNameDTO("Jhonny Azevedo");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employeeNameDTO.getName());
        when(employeeService.searchAnEmployee(employeeNameDTO.getName())).thenReturn(employeeDTO);

        // Act
        EmployeeDTO employeeSearched = employeeController.searchAnEmployee(employeeNameDTO);

        // Assert
        assertEquals(employeeNameDTO.getName(), employeeSearched.getName());
    }
}
