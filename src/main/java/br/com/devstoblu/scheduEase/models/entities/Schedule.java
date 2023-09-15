package br.com.devstoblu.scheduEase.models.entities;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

import java.util.Date;

@Entity
@Table(name = "tb_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    @Column(nullable = false)
    private String clientName;
    @Column(nullable = false)
    private String serviceBooked;
    @Column(nullable = false)
    private Date appointmentDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TimeGrid startTime;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TimeGrid endTime;

    public Schedule() {

    }

    public Schedule(Long id, Employee employeeId, String clientName, String serviceBooked
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
        return "Schedule{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", clientName='" + clientName + '\'' +
                ", serviceBooked='" + serviceBooked + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
