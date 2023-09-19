package br.com.devstoblu.scheduEase.services.interfaces;

import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;

import java.util.List;

public interface IEmployeeService {

    Long addEmployee(EmployeeDTO employeeDTO) throws Exception;
    void deleteEmployee(Long id);
    Long updateEmployee(EmployeeDTO employeeDTO) throws Exception;
    List<EmployeeDTO> listEmployees();
    EmployeeDTO searchAnEmployee(String name) throws Exception;
    EmployeeDTO searchAnEmployeeById(Long id);

}
