package com.smartclinic.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  List<Doctor> findBySpecialityIgnoreCase(String speciality);
}
