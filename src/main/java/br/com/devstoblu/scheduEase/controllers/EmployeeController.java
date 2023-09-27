package br.com.devstoblu.scheduEase.controllers;


import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Tag(name = "Funcionário", description = "Endpoints relacionados ao Funcionário") // customizando UI do Swagger
@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(description = "Cadastra um Funcionário", method = "POST")// customizando UI do Swagger
    @PostMapping
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        try {
            return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(description = "Exclui um Funcionário", method = "DELETE")// customizando UI do Swagger
    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @Operation(description = "Atualiza um cadastro de um Funcionário", method = "PUT")// customizando UI do Swagger
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

    @Operation(description = "Lista todos os Funcionários cadastrados", method = "GET")// customizando UI do Swagger
    @GetMapping("/list-all")
    public List<EmployeeDTO> listEmployees() {
        return employeeService.listEmployees();
    }

    @Operation(description = "Pesquisa um funcionário pelo nome", method = "GET")// customizando UI do Swagger
    @GetMapping
    public EmployeeDTO searchAnEmployee(@RequestParam(name = "employeeName") String employeeName) {

        try {
            return employeeService.searchAnEmployee(employeeName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
