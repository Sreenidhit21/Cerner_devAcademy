package com.devAcademy.patientManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devAcademy.patientManagement.entity.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>{

}
