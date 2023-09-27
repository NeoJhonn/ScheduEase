package br.com.devstoblu.scheduEase.services;


import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository repository;
    @Mock
    ModelMapper mapper;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void addEmployee_shouldAddEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Jhonny");
        employeeDTO.setActive(true);
        employeeDTO.setRole(EmployeeRole.HairStylist);
        Employee employee = new Employee();
        employee.setName("Mylena");
        employee.setActive(true);
        employee.setId(1l);
        employee.setRole(EmployeeRole.HairStylist);
        when(repository.findByName(employeeDTO.getName())).thenReturn(null);
        when(mapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);

        // Act
        Long id = employeeService.addEmployee(employeeDTO);

        // Assert
        assertNotNull(id);
    }

    @Test
    public void updateEmployee_shouldUpdateEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Jhonny");
        employeeDTO.setActive(true);
        employeeDTO.setRole(EmployeeRole.HairStylist);
        Employee employee = new Employee();
        employee.setName("Mylena");
        employee.setActive(true);
        employee.setId(1l);
        employee.setRole(EmployeeRole.Manicure);
        when(mapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);

        // Act
        Long id = employeeService.updateEmployee(employeeDTO);

        // Assert
        assertNotNull(id);
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
        }).when(repository).deleteById(id);

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        assertEquals(employees.size(), 1);
    }

    @Test
    public void listEmployees_shouldListEmployees() {
        // Arrange
        List<Employee> employees = List.of(new Employee(), new Employee());
        when(repository.findAll()).thenReturn(employees);

        // Act
        List<EmployeeDTO> employeesListed = employeeService.listEmployees();

        // Assert
        assertEquals(employees.size(), employeesListed.size());
    }

    @Test
    public void searchAnEmployee_shouldSearchAnEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO(1l, "Jhonny", EmployeeRole.HairStylist, true);
        Employee employee = new Employee();
        employee.setName("Jhonny");
        employee.setId(1l);
        employee.setActive(true);
        when(repository.findByName("Jhonny")).thenReturn(employee);
        when(mapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);

        // Act
         EmployeeDTO employeeSearched = employeeService.searchAnEmployee(employee.getName());

        // Assert
        assertEquals(employeeSearched.getName(), employee.getName());
    }

    @Test
    public void searchAnEmployeeById_shouldSearchAnEmployeeById() {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1l);
        Optional<Employee> employeeOpt = Optional.of(employee);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1l);
        when(repository.findById(employee.getId())).thenReturn(employeeOpt);
        when(mapper.map(employeeOpt, EmployeeDTO.class)).thenReturn(employeeDTO);

        // Act
        Long id = employeeService.searchAnEmployeeById(1l).getId();

        // Assert
        assertNotNull(id);
    }

    @Test
    public void save_shouldSave() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = new Employee();
        employee.setId(1l);
        when(mapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);

        // Act
        Long id = employeeService.save(employeeDTO);

        // Assert
        assertNotNull(id);
    }
}
