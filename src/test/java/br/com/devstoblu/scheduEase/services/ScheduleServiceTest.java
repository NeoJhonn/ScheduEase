package br.com.devstoblu.scheduEase.services;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import br.com.devstoblu.scheduEase.models.dtos.EmployeeDTO;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.models.entities.Schedule;
import br.com.devstoblu.scheduEase.repositories.ScheduleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Mock
    ScheduleRepository repository;
    @Mock
    ModelMapper mapper;

    @InjectMocks
    ScheduleService scheduleService;

    @Test
    public void createAnAppointment_shouldCreateAnAppointment_should() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO(9l, "Jhonny", 1l, "corte de cabelo", "2023-09-26", TimeGrid.H_21_30, TimeGrid.H_22_00);
        Schedule schedule = new Schedule();
        schedule.setId(7l);
        when(repository.verifyHasSameAppointment(scheduleDTO.getStartTime().toString(), scheduleDTO.getEndTime().toString(), scheduleDTO.getAppointmentDate(), scheduleDTO.getEmployeeId())).thenReturn(null);
        when(mapper.map(scheduleDTO, Schedule.class)).thenReturn(schedule);
        when(repository.save(schedule)).thenReturn(schedule);

        // Act
        long id = scheduleService.createAnAppointment(scheduleDTO);

        // Assert
        assertNotNull(id);
    }

    @Test
    public void updateAnAppointment_shouldUpdateAnAppointment() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        Schedule schedule = new Schedule();
        schedule.setId(1l);
        when(mapper.map(scheduleDTO, Schedule.class)).thenReturn(schedule);
        when(repository.save(schedule)).thenReturn(schedule);

        // Act
        Long id = scheduleService.updateAnAppointment(scheduleDTO);

        // Assert
        assertNotNull(id);
    }

    @Test
    public void deleteAnAppointment_shouldDeleteAnAppointment() {
        // Arrange
        Long id = 1l;
        repository = mock(ScheduleRepository.class);
        scheduleService.repository = repository;

        // Act
        scheduleService.deleteAnAppointment(id);

        // Assert
        Mockito.verify(repository, times(1)).deleteById(id);

    }

    @Test
    public void listAppointments_shouldListAppointments() {
        // Arrange
        ScheduleDTO scheduleDTO1 = new ScheduleDTO();
        scheduleDTO1.setAppointmentDate("2023-09-15");
        scheduleDTO1.setEmployeeId(1l);
        ScheduleDTO scheduleDTO2 = new ScheduleDTO();
        scheduleDTO2.setAppointmentDate("2023-09-15");
        scheduleDTO2.setEmployeeId(1l);
        List<ScheduleDTO> schedulesDTO = List.of(scheduleDTO1, scheduleDTO2);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(mapper.map(scheduleDTO1, Schedule.class));
        schedules.add(mapper.map(scheduleDTO2, Schedule.class));
        when(repository.listAppointments("2023-09-15", 1l)).thenReturn(schedules);
        //when(mapper.map(scheduleDTO1, ScheduleDTO.class)).thenReturn(scheduleDTO1);

        // Act
        List<ScheduleDTO> schedulesDTOListed = scheduleService.listAppointments("2023-09-15",1l);

        // Assert
        Assert.assertEquals(schedulesDTOListed.size(), 2);
    }

    @Test
    public void searchAnAppointment_shouldSearchAnAppointment() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setClientName("Johnny");
        scheduleDTO.setEmployeeId(1l);
        Employee employeeId = new Employee();
        employeeId.setId(1l);
        Schedule schedule = new Schedule();
        schedule.setClientName("Johnny");
        schedule.setEmployeeId(employeeId);
        when(repository.searchAnAppointment("Jhonny", 1l)).thenReturn(schedule);
        when(mapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        // Act
        ScheduleDTO scheduleDTOSearched = scheduleService.searchAnAppointment("Jhonny", 1l);

        // Assert
        Assert.assertEquals(scheduleDTOSearched, scheduleDTO);
    }

    @Test
    public void save_shouldSave() throws Exception {
        // Arrange
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        Schedule schedule = new Schedule();
        schedule.setId(1l);
        when(mapper.map(scheduleDTO, Schedule.class)).thenReturn(schedule);
        when(repository.save(schedule)).thenReturn(schedule);

        // Act
        Long id = scheduleService.save(scheduleDTO);

        // Assert
        assertNotNull(id);
    }

    @Test
    public void findAnAppointmentById_shouldFindAnAppointmentById() {
        // Arrange
        Long id = 1l;
        Schedule schedule = new Schedule();
        schedule.setId(id);
        Optional<Schedule> scheduleOpt = Optional.of(schedule);
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        schedule.setId(id);
        when(repository.findById(id)).thenReturn(scheduleOpt);
        when(mapper.map(scheduleOpt, ScheduleDTO.class)).thenReturn(scheduleDTO);

        // Act
        ScheduleDTO scheduleDTOSearched = scheduleService.findAnAppointmentById(id);

        // Assert
        Assert.assertEquals(scheduleDTO.getId(), scheduleDTOSearched.getId());
    }
}