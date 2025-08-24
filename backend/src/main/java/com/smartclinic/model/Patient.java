package com.smartclinic.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private String name;
  @Column(unique=true, nullable=false) private String email;
  @Column(unique=true, nullable=false) private String phone;
}
