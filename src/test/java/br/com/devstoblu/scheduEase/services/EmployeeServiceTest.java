package br.com.devstoblu.scheduEase.services;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.devstoblu.scheduEase.consts.ExceptionConsts.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;

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

    public void updateEmployee(EmployeeDTO employeeDTO) throws Exception {
//        return save(employeeDTO);
    }

    public void deleteEmployee(Long id) {


//        repository.deleteById(id);
    }


    public void listEmployees() {

//        List<EmployeeDTO> employees = repository.findAll().stream()
//                .map(e -> mapper.map(e, EmployeeDTO.class)).toList();
//
//        return employees;
    }

    public void searchAnEmployee(String name) throws Exception {

//        try {
//            EmployeeDTO employeeSearched = mapper.map(repository.findByName(name), EmployeeDTO.class);
//            if (employeeSearched == null) {
//                throw new Exception(EMPLOYEE_SEARCHED_NAME_ERROR);
//            }
//            return employeeSearched;
//        } catch (Exception e) {
//            throw new Exception(EMPLOYEE_SEARCHED_NAME_ERROR);
//        }
    }

    public void searchAnEmployeeById(Long id) {
//        return mapper.map(repository.findById(id), EmployeeDTO.class);
    }

    public void save(EmployeeDTO employeeDTO) throws Exception {


//        Boolean hasSameName = false;
//
//        try {
//            // verificar se já existe um profissional com o mesmo nome cadastrado
//            if (repository.findByName(employeeDTO.getName()) != null) {
//                hasSameName = true;
//                throw new Exception(EMPLOYEE_INSERT_NAME_ERROR);
//            }
//
//            Employee employee = mapper.map(employeeDTO, Employee.class);
//            Employee created = repository.save(employee);
//            return created.getId();
//
//        } catch (Exception e) {
//            if (hasSameName) {
//                throw new Exception(EMPLOYEE_INSERT_NAME_ERROR);
//            } else {
//                throw new Exception(EMPLOYEE_INSERT_ERROR);
//            }
//        }
    }
}
