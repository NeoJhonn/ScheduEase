package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDateIdDTO;
import br.com.devstoblu.scheduEase.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/schedules")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    public Long createAnAppointment(ScheduleDTO scheduleDTO) throws Exception {
        return null;
    }


    public Long updateAnAppointment(ScheduleDTO schedule) throws Exception {
        return null;
    }


    public void deleteAnAppointment(Long id) {

    }

    @GetMapping("/list-appointments")
    public List<ScheduleDTO> listAppointments(@Valid @RequestBody ScheduleDateIdDTO dateIdDTO) {
        return scheduleService.listAppointments(dateIdDTO.getAppointmentDate(), dateIdDTO.getId());
    }


    public ScheduleDTO searchAnAppointment(String clientName, Long employeeId) throws Exception {
        return null;
    }
}
