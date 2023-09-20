package br.com.devstoblu.scheduEase.services.interfaces;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;

import java.util.Date;
import java.util.List;

public interface IScheduleService {

    Long createAnAppointment(ScheduleDTO scheduleDTO) throws Exception;
    Long updateAnAppointment(ScheduleDTO schedule) throws Exception;
    void deleteAnAppointment(Long id);
    List<ScheduleDTO> listAppointments(String appointmentDate, Long employeeId);
    ScheduleDTO searchAnAppointment(String clientName, Long employeeId) throws Exception;
}
