package br.com.devstoblu.scheduEase.entities;

import br.com.devstoblu.scheduEase.enums.TimeGrid;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb-schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "employee_id")
    private Long employeeId;
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

    public Schedule(Long id, Long employeeId, String clientName, String serviceBooked
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
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


}
