package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import static org.mockito.Mockito.*;



@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void addEmployee_shouldAddEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO(1l, "Jhonny", EmployeeRole.HairStylist, true);
        String employeeJSON = objectMapper.writeValueAsString(employeeDTO);
        when(employeeService.addEmployee(employeeDTO)).thenReturn(employeeDTO.getId());

        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200

        // Assert
        verify(employeeService, times(0)).addEmployee(employeeDTO);
    }

    @Test
    public void deleteEmployee_shouldDeleteEmployee() throws Exception {
        // Arrange
        Long id = 1l;

        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", id)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200

        // Assert
        verify(employeeService, times(1)).deleteEmployee(id);
    }

    @Test
    public void updateEmployee_shouldUpdateEmployee() throws Exception {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO(1l, "Jhonny", EmployeeRole.HairStylist, true);
        when(employeeService.searchAnEmployeeById(employeeDTO.getId())).thenReturn(employeeDTO);
        // Converta o objeto EmployeeDTO em JSON
        String updatedEmployeeJSON = objectMapper.writeValueAsString(employeeDTO);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200 (OK)

        // Assert
        verify(employeeService, times(1)).updateEmployee(employeeDTO);
    }

    @Test
    public void listEmployees_shouldListEmployees() throws Exception {
        // Arrange
        List<EmployeeDTO> employeeDTOList = List.of(new EmployeeDTO(), new EmployeeDTO());
        when(employeeService.listEmployees()).thenReturn(employeeDTOList);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/list-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        // Assert
        verify(employeeService, times(1)).listEmployees();
    }

    @Test
    public void searchAnEmployee_shouldSearchAnEmployee() throws Exception {
        // Arrange
        String employeeName = "Jhonny Azevedo";
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employeeName);
        when(employeeService.searchAnEmployee(employeeName)).thenReturn(employeeDTO);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees?employeeName={employeeName}", employeeName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Assert
        verify(employeeService, times(1)).searchAnEmployee(employeeName);
    }
}
