package br.com.devstoblu.scheduEase.services;


import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.models.entities.Schedule;
import br.com.devstoblu.scheduEase.repositories.ScheduleRepository;
import br.com.devstoblu.scheduEase.services.interfaces.IScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static br.com.devstoblu.scheduEase.consts.ExceptionConsts.SCHEDULE_INSERT_ERROR;
import static br.com.devstoblu.scheduEase.consts.ExceptionConsts.SCHEDULE_SEARCHED_ERROR;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    ScheduleRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Long createAnAppointment(ScheduleDTO scheduleDTO) throws Exception {
        return save(scheduleDTO);
    }

    @Override
    public Long updateAnAppointment(ScheduleDTO schedule) throws Exception {
        return save(schedule);
    }

    @Override
    public void deleteAnAppointment(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ScheduleDTO> listAppointments(Date appointmentDate, Long employeeId) {
        List<ScheduleDTO> schedules = repository.listAppointments(appointmentDate, employeeId).stream().map(s -> mapper
                .map(s, ScheduleDTO.class)).toList();

        return schedules;
    }

    @Override
    public ScheduleDTO searchAnAppointment(String clientName, Long employeeId) throws Exception {
        try {

            return mapper.map(repository.searchAnAppointment(clientName, employeeId), ScheduleDTO.class);
        } catch (Exception e) {
            throw new Exception(SCHEDULE_SEARCHED_ERROR);
        }

    }

    public Long save(ScheduleDTO scheduleDTO) throws Exception {

        try {

            Schedule scheduled = mapper.map(scheduleDTO, Schedule.class);
            Schedule created = repository.save(scheduled);
            return created.getId();

        } catch (Exception e) {
            throw new Exception(SCHEDULE_INSERT_ERROR);
        }
    }
}
