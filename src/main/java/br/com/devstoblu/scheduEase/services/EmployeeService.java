package br.com.devstoblu.scheduEase.services;

import static br.com.devstoblu.scheduEase.consts.ExceptionConsts.*;

import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.repositories.EmployeeRepository;
import br.com.devstoblu.scheduEase.services.interfaces.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Long addEmployee(EmployeeDTO employeeDTO) throws Exception {
        return  save(employeeDTO);
    }

    @Override
    public Long updateEmployee(EmployeeDTO employeeDTO) throws Exception {
        return save(employeeDTO);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> listEmployees() {
        List<EmployeeDTO> employees = repository.findAll().stream()
                .map(e -> mapper.map(e, EmployeeDTO.class)).toList();

        return employees;
    }

    @Override
    public EmployeeDTO searchAnEmployee(String name) {

        return mapper.map(repository.findByName(name), EmployeeDTO.class);
    }

    public Long save(EmployeeDTO employeeDTO) throws Exception {

        try {
            Employee employee = mapper.map(employeeDTO, Employee.class);
            Employee created = repository.save(employee);
            return created.getId();

        } catch (Exception e) {
            throw new Exception(EMPLOYEE_INSERT_ERROR);
        }
    }

    
}
