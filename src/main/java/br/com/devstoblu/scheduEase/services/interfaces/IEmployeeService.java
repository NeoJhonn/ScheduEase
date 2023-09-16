package br.com.devstoblu.scheduEase.services.interfaces;

import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;

import java.util.List;

public interface IEmployeeService {

    void addEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    void updateEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> listEmployees();
    EmployeeDTO searchAnEmployee(String name);

}
