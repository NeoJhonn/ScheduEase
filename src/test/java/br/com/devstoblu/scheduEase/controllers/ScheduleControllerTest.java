package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleClientNameEmployeeIdDTO;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDateIdDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.models.entities.Schedule;
import br.com.devstoblu.scheduEase.services.ScheduleService;
import jakarta.validation.Valid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleControllerTest {

    @Mock
    ScheduleService scheduleService;
    @InjectMocks
    ScheduleController scheduleController;

    @Test
    public void createAnAppointment_shouldCreateAnAppointment() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO(1l, "Mariza da Silva", 1l,
                "escova", "2023-09-26", TimeGrid.H_8_30, TimeGrid.H_9_00);
        when(scheduleService.createAnAppointment(scheduleDTO)).thenReturn(1l);

        // Act
        ResponseEntity<Object> response = scheduleController.createAnAppointment(scheduleDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

    @Test
    public void updateAnAppointment_shouldUpdateAnAppointment() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1l);
        when(scheduleService.findAnAppointmentById(scheduleDTO.getId())).thenReturn(scheduleDTO);

        // Act
        ResponseEntity<Object> response = scheduleController.updateAnAppointment(scheduleDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteAnAppointment_shouldDeleteAnAppointment() {
        // Arrange
        Long id = 1l;
        Schedule schedule1 = new Schedule();
        schedule1.setId(id);
        Schedule schedule2 = new Schedule();
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);
        doAnswer((i) -> {
            schedules.remove(schedule1);
            return true;
        }).when(scheduleService).deleteAnAppointment(id);

        // Act
        scheduleController.deleteAnAppointment(id);

        // Assert
        Assert.assertEquals(schedules.size(), 1);
    }

    @Test
    public void listAppointments_shouldListAppointments() {
        // Arrange
        ScheduleDateIdDTO dateIdDTO = new ScheduleDateIdDTO();
        dateIdDTO.setId(1l);
        dateIdDTO.setAppointmentDate("2023-09-26");
        List<ScheduleDTO> scheduleDTOS = List.of(new ScheduleDTO(), new ScheduleDTO());
        when(scheduleService.listAppointments(dateIdDTO.getAppointmentDate(), dateIdDTO.getId())).thenReturn(scheduleDTOS);

        // Act
        List<ScheduleDTO> scheduleDTOListed = scheduleController.listAppointments(dateIdDTO);

        // Assert
        assertEquals(scheduleDTOListed, scheduleDTOS);
    }

    @Test
    public void searchAnAppointment_shouldSearchAnAppointment() throws Exception {
        // Arrange
        ScheduleClientNameEmployeeIdDTO employee = new ScheduleClientNameEmployeeIdDTO();
        employee.setEmployeeId(1l);
        employee.setClientName("Mariza Alves");
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setClientName(employee.getClientName());
        scheduleDTO.setEmployeeId(employee.getEmployeeId());
        when(scheduleService.searchAnAppointment(employee.getClientName(), employee.getEmployeeId())).thenReturn(scheduleDTO);

        // Act
        ScheduleDTO scheduleDTOSearched = scheduleController.searchAnAppointment(employee);

        // Assert
        assertEquals(scheduleDTOSearched.getClientName(), scheduleDTO.getClientName());
        assertEquals(scheduleDTOSearched.getEmployeeId(), scheduleDTO.getEmployeeId());
    }
}
