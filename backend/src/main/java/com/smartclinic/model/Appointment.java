package com.smartclinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Appointment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) private Doctor doctor;
  @ManyToOne(optional=false) private Patient patient;

  @NotNull @Future
  private LocalDateTime startTime;

  @NotNull @Future
  private LocalDateTime endTime;

  @Enumerated(EnumType.STRING)
  private AppointmentStatus status = AppointmentStatus.BOOKED;
}
