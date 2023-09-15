package br.com.devstoblu.scheduEase.repositories;

import br.com.devstoblu.scheduEase.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);
}
