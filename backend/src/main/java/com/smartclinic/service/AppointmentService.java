package com.smartclinic.service;

import com.smartclinic.model.*;
import com.smartclinic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
  private final AppointmentRepository apptRepo;
  private final DoctorRepository doctorRepo;
  private final PatientRepository patientRepo;

  public Appointment bookAppointment(Long patientId, Long doctorId, LocalDateTime start) {
    var d = doctorRepo.findById(doctorId).orElseThrow();
    var p = patientRepo.findById(patientId).orElseThrow();
    var a = Appointment.builder()
        .doctor(d)
        .patient(p)
        .startTime(start)
        .endTime(start.plusMinutes(30))
        .status(AppointmentStatus.BOOKED)
        .build();
    return apptRepo.save(a);
  }

  public List<Appointment> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate date) {
    LocalDateTime from = date.atStartOfDay();
    LocalDateTime to = from.plusDays(1);
    return apptRepo.findByDoctorIdAndStartTimeBetween(doctorId, from, to);
  }
}
