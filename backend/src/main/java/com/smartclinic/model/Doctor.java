package com.smartclinic.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Doctor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private String name;
  @Column(nullable=false) private String speciality;
  @Column(nullable=false) private LocalTime availableFrom;
  @Column(nullable=false) private LocalTime availableTo;
  @Column(unique=true, nullable=false) private String email;
}
