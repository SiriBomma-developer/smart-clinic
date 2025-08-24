package com.smartclinic.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
  Optional<Patient> findByEmail(String email);
  Optional<Patient> findByEmailOrPhone(String email, String phone);
}
