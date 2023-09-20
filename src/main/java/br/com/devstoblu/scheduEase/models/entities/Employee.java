package br.com.devstoblu.scheduEase.models.entities;

import br.com.devstoblu.scheduEase.enums.EmployeeRole;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(value = EnumType.STRING)
    private EmployeeRole role;
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<Schedule> scheduleList;
    @Column(nullable = false)
    private Boolean isActive;

    public Employee() {

    }

    public Employee(Long id, String name, EmployeeRole role, List<Schedule> scheduleList, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.scheduleList = scheduleList;
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

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", scheduleList=" + scheduleList +
                ", isActive=" + isActive +
                '}';
    }
}
