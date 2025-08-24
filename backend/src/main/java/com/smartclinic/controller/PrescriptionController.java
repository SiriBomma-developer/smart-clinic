package com.smartclinic.controller;

import com.smartclinic.dto.PrescriptionRequest;
import com.smartclinic.model.Appointment;
import com.smartclinic.model.Prescription;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.repository.PrescriptionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
  private final PrescriptionRepository presRepo;
  private final AppointmentRepository apptRepo;

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody PrescriptionRequest req) {
    Appointment a = apptRepo.findById(req.getAppointmentId()).orElse(null);
    if (a == null) return ResponseEntity.badRequest().body("{"error":"Invalid appointmentId"}");
    Prescription p = Prescription.builder().appointment(a).notes(req.getNotes()).build();
    presRepo.save(p);
    return ResponseEntity.status(201).body(p);
  }
}
