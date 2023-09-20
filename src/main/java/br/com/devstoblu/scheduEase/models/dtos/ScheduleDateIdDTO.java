package br.com.devstoblu.scheduEase.models.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ScheduleDateIdDTO {

    @NotNull(message = "Selecione uma data para listar os horários.")
    private String appointmentDate;

    @NotNull
    private Long id;

    public ScheduleDateIdDTO() {

    }

    public ScheduleDateIdDTO(String appointmentDate, Long id) {
        this.appointmentDate = appointmentDate;
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
