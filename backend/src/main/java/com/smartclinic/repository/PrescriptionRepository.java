package com.smartclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartclinic.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> { }
