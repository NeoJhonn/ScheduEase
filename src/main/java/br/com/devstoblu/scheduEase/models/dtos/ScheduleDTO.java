package br.com.devstoblu.scheduEase.models.dtos;

import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.enums.TimeGrid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class ScheduleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;
    private Long id;

    @NotNull(message = "O horário precisa esta vinculado a um profissional.")
    private Employee employeeId;

    @NotBlank(message = "O campo nome do Cliente não pode estar em branco.")
    @NotEmpty(message = "O campo nome do Cliente não pode estar vazio.")
    private String clientName;

    @NotBlank(message = "O campo Serviço Agendado não pode estar em branco.")
    @NotEmpty(message = "O campo Serviço Agendado não pode estar vazio.")
    private String serviceBooked;

    @NotNull(message = "Selecione uma data para fazer o agendamento.")
    private Date appointmentDate;

    @NotBlank(message = "O campo Horário Início não pode estar em branco.")
    @NotEmpty(message = "O campo Horário Início não pode estar vazio.")
    private TimeGrid startTime;

    @NotBlank(message = "O campo Horário Fim não pode estar em branco.")
    @NotEmpty(message = "O campo Horário Fim não pode estar vazio.")
    private TimeGrid endTime;

    public ScheduleDTO() {

    }

    public ScheduleDTO(Long id, Employee employeeId, String clientName, String serviceBooked
            , Date appointmentDate, TimeGrid startTime, TimeGrid endTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.clientName = clientName;
        this.serviceBooked = serviceBooked;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceBooked() {
        return serviceBooked;
    }

    public void setServiceBooked(String serviceBooked) {
        this.serviceBooked = serviceBooked;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public TimeGrid getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeGrid startTime) {
        this.startTime = startTime;
    }

    public TimeGrid getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeGrid endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "id=" + id +
                ", Profissional=" + employeeId.getName() +
                ", clientName='" + clientName + '\'' +
                ", serviceBooked='" + serviceBooked + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
