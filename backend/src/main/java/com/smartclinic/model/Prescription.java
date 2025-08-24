package com.smartclinic.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Prescription {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) private Appointment appointment;
  @Column(columnDefinition="TEXT") private String notes;
  private LocalDateTime createdAt = LocalDateTime.now();
}
