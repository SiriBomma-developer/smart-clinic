package com.smartclinic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionRequest {
  @NotNull private Long appointmentId;
  @NotNull private String notes;
}
