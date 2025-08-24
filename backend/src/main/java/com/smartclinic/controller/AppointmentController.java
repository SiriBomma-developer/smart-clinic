package com.smartclinic.controller;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.Patient;
import com.smartclinic.repository.PatientRepository;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
  private final AppointmentService appointmentService;
  private final AppointmentRepository apptRepo;
  private final PatientRepository patientRepo;

  @PostMapping("/book")
  public ResponseEntity<?> book(@RequestParam Long patientId, @RequestParam Long doctorId,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start) {
    Appointment a = appointmentService.bookAppointment(patientId, doctorId, start);
    return ResponseEntity.ok(a);
  }

  @GetMapping("/doctor/{doctorId}")
  public List<Appointment> getForDoctorOnDate(@PathVariable Long doctorId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return appointmentService.getAppointmentsForDoctorOnDate(doctorId, date);
  }

  @GetMapping("/my")
  public ResponseEntity<?> myAppointments() {
    String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
    Patient p = patientRepo.findByEmail(email).orElse(null);
    if (p == null) return ResponseEntity.status(404).body("{"error":"Patient not found"}");
    List<Appointment> list = apptRepo.findByPatientId(p.getId());
    return ResponseEntity.ok(list);
  }
}
