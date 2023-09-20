package br.com.devstoblu.scheduEase.services.interfaces;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;

import java.util.Date;
import java.util.List;

public interface IScheduleService {

    Long createAnAppointment(ScheduleDTO scheduleDTO) throws Exception;
    List<ScheduleDTO> listAppointments(Date appointmentDate, Long employeeId);
    void deleteAnAppointment(Long id);
    Long updateAnAppointment(ScheduleDTO schedule) throws Exception;
    ScheduleDTO searchAnAppointment(String clientName, Long employeeId) throws Exception;
}
