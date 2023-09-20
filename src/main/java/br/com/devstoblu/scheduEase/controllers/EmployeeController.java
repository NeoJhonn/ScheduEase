package br.com.devstoblu.scheduEase.controllers;


import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeNameDTO;
import br.com.devstoblu.scheduEase.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import java.util.List;


@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        try {
            return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        //Verificando se o Employee existe
        EmployeeDTO existingEmployeee = employeeService.searchAnEmployeeById(employeeDTO.getId());

        if (existingEmployeee == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Copie os atributos de employeeDTO para o existingEmployeee usando o BeanUtils
            BeanUtils.copyProperties(employeeDTO, existingEmployeee);
            // Salve a atualização no banco de dados
            return ResponseEntity.ok(employeeService.updateEmployee(existingEmployeee));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list-all")
    public List<EmployeeDTO> listEmployees() {
        return employeeService.listEmployees();
    }

    @GetMapping
    public EmployeeDTO searchAnEmployee(@Valid @RequestBody EmployeeNameDTO employeeNameDTO) {

        try {
            return employeeService.searchAnEmployee(employeeNameDTO.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
