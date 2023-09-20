package br.com.devstoblu.scheduEase.models.dtos;

import jakarta.validation.constraints.NotNull;

public class ScheduleClientNameEmployeeIdDTO {

    @NotNull(message = "Insira o Nome do Cliente")
    private String clientName;
    @NotNull
    private Long employeeId;

    public ScheduleClientNameEmployeeIdDTO() {

    }

    public ScheduleClientNameEmployeeIdDTO(String clientName, Long employeeId) {
        this.clientName = clientName;
        this.employeeId = employeeId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
