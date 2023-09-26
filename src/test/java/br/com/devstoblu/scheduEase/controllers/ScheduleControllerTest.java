package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.services.ScheduleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    // Arrange
    // Act
    // Assert
}
