package br.com.devstoblu.scheduEase.services;

import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.repositories.EmployeeRepository;
import br.com.devstoblu.scheduEase.repositories.ScheduleRepository;
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
    public void addEmployee(EmployeeDTO employeeDTO) {

    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {

    }

    @Override
    public List<EmployeeDTO> listEmployees() {
        return null;
    }

    @Override
    public EmployeeDTO searchAnEmployee(String name) {
        return null;
    }
}
