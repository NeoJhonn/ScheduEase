package br.com.devstoblu.scheduEase.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;

public class EmployeeNameDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    @NotBlank(message = "O campo nome não pode estar em branco.")
    @NotEmpty(message = "O campo nome não pode estar vazio.")
    private String name;

    public EmployeeNameDTO () {

    }

    public EmployeeNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
