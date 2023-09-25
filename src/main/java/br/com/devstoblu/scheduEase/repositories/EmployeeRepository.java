package br.com.devstoblu.scheduEase.repositories;

import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);
}
