package br.com.devstoblu.scheduEase.repositories;

import br.com.devstoblu.scheduEase.entities.Employee;
import br.com.devstoblu.scheduEase.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /* Pesquisar pelo nome do cliente e profissional */
    @Query(nativeQuery = true, value = """
            SELECT * FROM tb-schedule 
            JOIN tb_employee ON tb-schedule.employee_id = :employeeId
            WHERE tb-schedule.client_name = :clientName
            """)
    Schedule searchAnAppointment(String clientName, Long employeeId);

    /* Listar horários pela Data e profissional */
    @Query(nativeQuery = true, value = """
            SELECT * FROM tb-schedule
            JOIN tb_employee ON tb-schedule.employee_id = :employeeId
            WHERE tb-schedule.appointment_date = :appointmentDate
            ORDER BY tb-schedule.appointment_date DESC
            """)
    List<Schedule> listAppointments(Date appointmentDate, Long employeeId);

}
