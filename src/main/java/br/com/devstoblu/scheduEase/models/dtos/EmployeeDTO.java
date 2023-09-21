package br.com.devstoblu.scheduEase.models.dtos;

import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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

    @NotNull(message = "Selecione um cargo.")
    private EmployeeRole role;

    private Boolean isActive = true;

    public EmployeeDTO() {

    }

    public EmployeeDTO(Long id, String name, EmployeeRole role, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.role = role;
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
                ", isActive=" + isActive +
                '}';
    }
}
