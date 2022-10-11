package com.devAcademy.patientManagement.service;

import com.devAcademy.patientManagement.entity.PatientEntity;

public interface PatientService {
	PatientEntity createPatientDetails(PatientEntity patientEntity);
	PatientEntity updatePatientDetails(PatientEntity patientEntity);
	PatientEntity getPatientDetailsById(Long patientId);
	void deletePatientDetails(Long patientId);
	

}
