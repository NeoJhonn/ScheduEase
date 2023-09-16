package br.com.devstoblu.scheduEase.services;

import br.com.devstoblu.scheduEase.models.dtos.ScheduleDTO;
import br.com.devstoblu.scheduEase.repositories.ScheduleRepository;
import br.com.devstoblu.scheduEase.services.interfaces.IScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    ScheduleRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public void createAnAppointment(ScheduleDTO scheduleDTO) {

    }

    @Override
    public List<ScheduleDTO> listAppointments(Date appointmentDate, Long employeeId) {
        return null;
    }

    @Override
    public void deleteAnAppointment(Long id) {

    }

    @Override
    public void updateAnAppointment(ScheduleDTO schedule) {

    }

    @Override
    public ScheduleDTO searchAnAppointment(String clientName, Long employeeId) {
        return null;
    }
}
