package br.com.devstoblu.scheduEase.dtos;

import br.com.devstoblu.scheduEase.entities.Schedule;
import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class EmployeeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;
    private Long id;

    @NotBlank(message = "O campo nome não pode estar em branco.")
    @NotEmpty(message = "O campo nome não pode estar vazio.")
    private String name;

    @NotBlank(message = "O campo função não pode estar em branco.")
    @NotEmpty(message = "O campo função não pode estar vazio.")
    private EmployeeRole role;

    private List<ScheduleDTO> scheduleListDTO;

    private Boolean isActive = true;

    public EmployeeDTO() {

    }

    public EmployeeDTO(Long id, String name, EmployeeRole role, List<ScheduleDTO> scheduleListDTO, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.scheduleListDTO = scheduleListDTO;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public List<ScheduleDTO> getScheduleListDTO() {
        return scheduleListDTO;
    }

    public void setScheduleList(List<ScheduleDTO> scheduleListDTO) {
        this.scheduleListDTO = scheduleListDTO;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", scheduleList=" + scheduleListDTO +
                ", isActive=" + isActive +
                '}';
    }
}
