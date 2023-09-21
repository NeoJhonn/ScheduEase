package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleClientNameEmployeeIdDTO;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.models.dtos.ScheduleDateIdDTO;
import br.com.devstoblu.scheduEase.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/schedules")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Object> createAnAppointment( @RequestBody ScheduleDTO scheduleDTO) throws Exception {

        try {
            return ResponseEntity.ok(scheduleService.createAnAppointment(scheduleDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateAnAppointment(@Valid @RequestBody ScheduleDTO scheduleDTO) throws Exception {
        //Verificando se o horário existe
        ScheduleDTO existingSchedule = scheduleService.findAnAppointmentById(scheduleDTO.getId());

        if (existingSchedule == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Copie os atributos de scheduleDTO para o existingSchedule usando o BeanUtils
            BeanUtils.copyProperties(scheduleDTO, existingSchedule);
            // Salve a atualização no banco de dados
            return ResponseEntity.ok(scheduleService.updateAnAppointment(existingSchedule));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAnAppointment(@PathVariable Long id) {
        scheduleService.deleteAnAppointment(id);
    }

    @GetMapping("/list-appointments")
    public List<ScheduleDTO> listAppointments(@Valid @RequestBody ScheduleDateIdDTO dateIdDTO) {
        return scheduleService.listAppointments(dateIdDTO.getAppointmentDate(), dateIdDTO.getId());
    }

    @GetMapping
    public ScheduleDTO searchAnAppointment(@Valid @RequestBody ScheduleClientNameEmployeeIdDTO employee) throws Exception {

        try {
            return scheduleService.searchAnAppointment(employee.getClientName(), employee.getEmployeeId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
