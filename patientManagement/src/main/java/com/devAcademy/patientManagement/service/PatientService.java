package com.devAcademy.patientManagement.service;

import java.util.List;
import java.util.Optional;

import com.devAcademy.patientManagement.entity.PatientEntity;

public interface PatientService {
	PatientEntity createPatientDetails(PatientEntity patientEntity);

	PatientEntity updatePatientDetails(PatientEntity patientEntity);

	Optional<PatientEntity> getPatientDetailsById(Long patientId);

	PatientEntity getPatientDetailsByGovtId(String patientGovtId);

	List<PatientEntity> getPatientDetailsByName(String patientName);

	void deletePatientDetails(Long patientId);

}
