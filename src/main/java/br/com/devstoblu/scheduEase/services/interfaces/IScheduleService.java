package br.com.devstoblu.scheduEase.services.interfaces;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;

import java.util.Date;
import java.util.List;

public interface IScheduleService {

    void createAnAppointment(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> listAppointments(Date appointmentDate, Long employeeId);
    void deleteAnAppointment(Long id);
    void updateAnAppointment(ScheduleDTO schedule);
    ScheduleDTO searchAnAppointment(String clientName, Long employeeId);
}
