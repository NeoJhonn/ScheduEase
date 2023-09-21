package br.com.devstoblu.scheduEase.repositories;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import br.com.devstoblu.scheduEase.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /* Pesquisar pelo nome do cliente e profissional */
    @Query(nativeQuery = true, value = """
             SELECT * FROM tb_schedule s
             WHERE s.client_name = :clientName AND s.employee_id = :employeeId
            """)
    Schedule searchAnAppointment(String clientName, Long employeeId);

    /* Listar horários pela Data e profissional */
    @Query(nativeQuery = true, value = """
             SELECT * FROM tb_schedule s
             WHERE s.appointment_date = :appointmentDate AND s.employee_id = :employeeId
             ORDER BY s.appointment_date DESC
            """)
    List<Schedule> listAppointments(String appointmentDate, Long employeeId);

    /* Verificar se já existe um horário agendado numa data para um profissional  */
    @Query(nativeQuery = true, value = """
             SELECT * FROM tb_schedule s
             WHERE s.start_time = :startTime 
             AND s.end_time = :endTime 
             AND s.appointment_date = :appointmentDate 
             AND s.employee_id = :employeeId
            """)
    Schedule verifyHasSameAppointment(TimeGrid startTime, TimeGrid endTime, String appointmentDate, Long employeeId);
}
