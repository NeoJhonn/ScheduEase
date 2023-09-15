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
             SELECT * FROM tb_schedule
             JOIN tb_employee ON tb_schedule.employee_id = tb_employee.id
             WHERE tb_schedule.appointment_date = :appointmentDate AND tb_employee.id = :employeeId
             ORDER BY tb_schedule.appointment_date DESC
            """)
    List<Schedule> listAppointments(Date appointmentDate, Long employeeId);

}
