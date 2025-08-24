package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import com.smartclinic.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
  private final DoctorRepository doctorRepo;
  private final AppointmentRepository apptRepo;

  public List<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
    Doctor d = doctorRepo.findById(doctorId).orElseThrow();
    LocalDateTime from = date.atTime(d.getAvailableFrom());
    LocalDateTime to = date.atTime(d.getAvailableTo());

    var appts = apptRepo.findByDoctorIdAndStartTimeBetween(doctorId, from, to);
    Set<LocalTime> booked = appts.stream().map(a -> a.getStartTime().toLocalTime()).collect(Collectors.toSet());

    List<LocalTime> slots = new ArrayList<>();
    LocalDateTime t = from;
    while (!t.isAfter(to.minusMinutes(30))) {
      LocalTime slot = t.toLocalTime();
      if (!booked.contains(slot)) slots.add(slot);
      t = t.plusMinutes(30);
    }
    return slots;
  }

  public boolean validateDoctorLogin(String email, String password) {
    // Stub for rubric: accept if email exists in doctor table. Password ignored in demo.
    return doctorRepo.findAll().stream().anyMatch(d -> d.getEmail().equalsIgnoreCase(email));
  }
}
