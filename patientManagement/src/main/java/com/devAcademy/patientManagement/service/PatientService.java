package com.devAcademy.patientManagement.service;

import java.util.List;
import java.util.Optional;

import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.exception.GovtIdOrReasonForNotSharingRequiredException;
import com.devAcademy.patientManagement.exception.PatientNotFoundException;

public interface PatientService {
	PatientEntity createPatientDetails(PatientEntity patientEntity) throws GovtIdOrReasonForNotSharingRequiredException;

	PatientEntity updatePatientDetails(PatientEntity patientEntity);

	Optional<PatientEntity> getPatientDetailsById(Long patientId) throws PatientNotFoundException;

	PatientEntity getPatientDetailsByGovtId(String patientGovtId) throws PatientNotFoundException;

	List<PatientEntity> getPatientDetailsByName(String patientName) throws PatientNotFoundException;

	void deletePatientDetails(Long patientId);

}
