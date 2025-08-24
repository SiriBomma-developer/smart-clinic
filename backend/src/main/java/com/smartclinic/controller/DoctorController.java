package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import com.smartclinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorController {
  private final DoctorRepository doctorRepo;
  private final DoctorService doctorService;

  @GetMapping("/doctors")
  public List<Map<String,Object>> all() {
    return doctorRepo.findAll().stream().map(d -> Map.of(
        "id", d.getId(),
        "name", d.getName(),
        "speciality", d.getSpeciality(),
        "email", d.getEmail(),
        "availableFrom", d.getAvailableFrom().toString(),
        "availableTo", d.getAvailableTo().toString()
    )).collect(Collectors.toList());
  }

  @GetMapping("/doctors/{id}/availability")
  public ResponseEntity<?> getAvailability(@PathVariable Long id,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    List<LocalTime> slots = doctorService.getAvailableTimeSlots(id, date);
    return ResponseEntity.ok(Map.of("doctorId", id, "date", date.toString(),
        "slots", slots.stream().map(LocalTime::toString).toList()));
  }

  @GetMapping("/doctors/search")
  public List<Map<String,Object>> search(
      @RequestParam String speciality,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime to) {
    return doctorRepo.findBySpecialityIgnoreCase(speciality).stream()
        .filter(d -> !d.getAvailableFrom().isAfter(from) && !d.getAvailableTo().isBefore(to))
        .map(d -> Map.of("id", d.getId(), "name", d.getName(), "speciality", d.getSpeciality()))
        .collect(Collectors.toList());
  }
}
