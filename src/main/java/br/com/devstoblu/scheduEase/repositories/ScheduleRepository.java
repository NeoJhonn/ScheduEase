package br.com.devstoblu.scheduEase.repositories;

import br.com.devstoblu.scheduEase.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /* Pesquisar pelo nome do cliente e profissional */
    @Query(nativeQuery = true, value = """
             SELECT * FROM tb_schedule
             JOIN tb_employee ON tb_schedule.employee_id = tb_employee.id
             WHERE tb_schedule.client_name = :clientName AND tb_employee.id = :employeeId
            """)
    Schedule searchAnAppointment(String clientName, Long employeeId);

    /* Listar horários pela Data e profissional */
    @Query(nativeQuery = true, value = """
             SELECT
                 s.id AS schedule_id,
                 s.appointment_date,
                 s.client_name,
                 s.start_time,
                 s.end_time,
                 s.service_booked,
                 e.id AS employee_id,
             FROM tb_schedule s
             JOIN tb_employee e ON s.employee_id = e.id
             WHERE s.appointment_date = :appointmentDate AND e.id = :employeeId
             ORDER BY s.appointment_date DESC
            """)
    List<Schedule> listAppointments(String appointmentDate, Long employeeId);

}
