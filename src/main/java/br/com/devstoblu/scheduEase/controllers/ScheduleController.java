package br.com.devstoblu.scheduEase.controllers;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Agenda", description = "Endpoints relacionados a Agenda") // customizando UI do Swagger
@RestController
@RequestMapping(value = "/api/schedules")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Operation(description = "Adiciona uma horário", method = "POST")// customizando UI do Swagger
    @PostMapping
    public ResponseEntity<Object> createAnAppointment(@Valid @RequestBody ScheduleDTO scheduleDTO) throws Exception {

        try {
            return ResponseEntity.ok(scheduleService.createAnAppointment(scheduleDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(description = "Atualiza um horário", method = "PUT")// customizando UI do Swagger
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

    @Operation(description = "Exclui um horário", method = "DELETE")// customizando UI do Swagger
    @DeleteMapping(value = "/{id}")
    public void deleteAnAppointment(@PathVariable Long id) {
        scheduleService.deleteAnAppointment(id);
    }

    @Operation(description = "Lista todos os horários do Funcionário pela data e seu Id", method = "GET")// customizando UI do Swagger
    @GetMapping(value = "/list-appointments")
    public List<ScheduleDTO> listAppointments(@RequestParam(name = "appointmentDate") String appointmentDate,
                                              @RequestParam(name = "id") Long id) {
        return scheduleService.listAppointments(appointmentDate, id);
    }

    @Operation(description = "Pesquisa um horário pelo nome do Cliente e id do Funcionário", method = "GET")// customizando UI do Swagger
    @GetMapping
    public ScheduleDTO searchAnAppointment(@RequestParam(name = "clientName") String clientName,
                                           @RequestParam(name = "employeeId") Long employeeId) throws Exception {

        try {
            return scheduleService.searchAnAppointment(clientName, employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
