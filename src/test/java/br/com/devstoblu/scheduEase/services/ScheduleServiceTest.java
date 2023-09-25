package br.com.devstoblu.scheduEase.services;

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
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Mock
    ScheduleRepository repository;
    @Mock
    ModelMapper mapper;

    @InjectMocks
    ScheduleService scheduleService;

    public void createAnAppointment(ScheduleDTO scheduleDTO) throws Exception {
        // Arrange
        // Act
        // Assert


        // Verificando se já existe um cliente agendado neste horário
        //Schedule existingSchedule = repository.verifyHasSameAppointment(scheduleDTO.getStartTime().toString(), scheduleDTO.getEndTime().toString(), scheduleDTO.getAppointmentDate(), scheduleDTO.getEmployeeId());

        //try{
            ///if (existingSchedule != null) {
                //throw new Exception(SCHEDULE_HAS_SAME_ERROR);
            //}

            //return save(scheduleDTO);
        //} catch (Exception e) {
           // throw new Exception(SCHEDULE_HAS_SAME_ERROR);
       //}
    }

    public void updateAnAppointment(ScheduleDTO schedule) throws Exception {
        // Arrange
        // Act
        // Assert

        //return save(schedule);
    }


    public void deleteAnAppointment(Long id) {
        // Arrange
        // Act
        // Assert

        //repository.deleteById(id);
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
        when(mapper.map(scheduleDTO1, ScheduleDTO.class)).thenReturn(scheduleDTO1);
        when(mapper.map(scheduleDTO2, ScheduleDTO.class)).thenReturn(scheduleDTO2);


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