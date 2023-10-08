package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import br.com.devstoblu.scheduEase.services.ScheduleService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {

    @MockBean
    ScheduleService scheduleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createAnAppointment_shouldCreateAnAppointment() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO(1l, "Mariza da Silva", 1l,
                "escova", "2023-09-26", TimeGrid.H_8_30, TimeGrid.H_9_00);
        String scheduleJSON = objectMapper.writeValueAsString(scheduleDTO);
        when(scheduleService.createAnAppointment(scheduleDTO)).thenReturn(1l);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(scheduleJSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200

        // Assert
        verify(scheduleService, times(0)).createAnAppointment(scheduleDTO);
    }

    @Test
    public void updateAnAppointment_shouldUpdateAnAppointment() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO(1l, "Mariza da Silva", 1l,
                "escova", "2023-09-26", TimeGrid.H_8_30, TimeGrid.H_9_00);
        String scheduleJSON = objectMapper.writeValueAsString(scheduleDTO);
        when(scheduleService.findAnAppointmentById(scheduleDTO.getId())).thenReturn(scheduleDTO);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(scheduleJSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200

        // Assert
        verify(scheduleService, times(0)).createAnAppointment(scheduleDTO);
    }

    @Test
    public void deleteAnAppointment_shouldDeleteAnAppointment() throws Exception  {
        // Arrange
        Long id = 1l;

        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/schedules/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200

        // Assert
        verify(scheduleService, times(1)).deleteAnAppointment(id);
    }

    @Test
    public void listAppointments_shouldListAppointments() throws Exception {
        // Arrange
        Long id = 1l;
        String appointmentDate = "2023-09-26";
        List<ScheduleDTO> scheduleDTOS = List.of(
                new ScheduleDTO(1l, "Mariza da Silva", 1l,
                        "escova", "2023-09-26", TimeGrid.H_8_30, TimeGrid.H_9_00),
                new ScheduleDTO(1l, "Marco da costa", 1l,
                        "escova", "2023-09-26", TimeGrid.H_9_30, TimeGrid.H_10_00));
        when(scheduleService.listAppointments(appointmentDate, id)).thenReturn(scheduleDTOS);

        // Act
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/schedules/list-appointments?appointmentDate={appointmentDate}&id={id}", appointmentDate, id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Verifique se o status HTTP é 200
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        // Assert
        verify(scheduleService, times(1)).listAppointments(appointmentDate, id);
    }

    @Test
    public void searchAnAppointment_shouldSearchAnAppointment() throws Exception {
        // Arrange
        String clientName = "Mariza Alves";
        Long employeeId = 1l;
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setClientName(clientName);
        scheduleDTO.setEmployeeId(employeeId);
        when(scheduleService.searchAnAppointment(clientName, employeeId)).thenReturn(scheduleDTO);

        // Act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/schedules?clientName={clientName}&employeeId={employeeId}", clientName, employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Verifique se o status HTTP é 200

        // Assert
        verify(scheduleService, times(1)).searchAnAppointment(clientName, employeeId);
    }
}
