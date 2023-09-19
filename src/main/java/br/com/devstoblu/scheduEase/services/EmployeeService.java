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
    public EmployeeDTO searchAnEmployee(String name) throws Exception {

        try {
            EmployeeDTO employeeSearched = mapper.map(repository.findByName(name), EmployeeDTO.class);
            if (employeeSearched == null) {
                throw new Exception(EMPLOYEE_SEARCHED_NAME_ERROR);
            }
            return employeeSearched;
        } catch (Exception e) {
            throw new Exception(EMPLOYEE_SEARCHED_NAME_ERROR);
        }
    }

    @Override
    public EmployeeDTO searchAnEmployeeById(Long id) {
        return mapper.map(repository.findById(id), EmployeeDTO.class);
    }

    public Long save(EmployeeDTO employeeDTO) throws Exception {
        Boolean hasSameName = false;

        try {
            // verificar se já existe um profissional com o mesmo nome cadastrado
            if (repository.findByName(employeeDTO.getName()) != null) {
                hasSameName = true;
                throw new Exception(EMPLOYEE_INSERT_NAME_ERROR);
            }

            Employee employee = mapper.map(employeeDTO, Employee.class);
            Employee created = repository.save(employee);
            return created.getId();

        } catch (Exception e) {
            if (hasSameName) {
                throw new Exception(EMPLOYEE_INSERT_NAME_ERROR);
            } else {
                throw new Exception(EMPLOYEE_INSERT_ERROR);
            }
        }
    }
}
