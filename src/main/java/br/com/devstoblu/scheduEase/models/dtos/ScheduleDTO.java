package br.com.devstoblu.scheduEase.models.dtos;

import br.com.devstoblu.scheduEase.models.entities.Employee;
import br.com.devstoblu.scheduEase.enums.TimeGrid;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @NotBlank(message = "O campo nome do Cliente não pode estar em branco.")
    @NotEmpty(message = "O campo nome do Cliente não pode estar vazio.")
    private String clientName;

    @NotNull
    private Long employeeId;

    @NotBlank(message = "O campo Serviço Agendado não pode estar em branco.")
    @NotEmpty(message = "O campo Serviço Agendado não pode estar vazio.")
    private String serviceBooked;

    @NotNull(message = "Selecione uma data para fazer o agendamento.")
    private String appointmentDate;

    @NotNull(message = "Selecione uma Horário de Início para fazer o agendamento.")
    private TimeGrid startTime;

    @NotNull(message = "Selecione uma Horário de Fim para fazer o agendamento.")
    private TimeGrid endTime;

    public ScheduleDTO() {

    }

    public ScheduleDTO(Long id, String clientName, Long employeeId, String serviceBooked
            , String appointmentDate, TimeGrid startTime, TimeGrid endTime) {
        this.id = id;
        this.clientName = clientName;
        this.employeeId = employeeId;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", serviceBooked='" + serviceBooked + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
