package com.devAcademy.patientManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.repository.PatientRepository;
import com.devAcademy.patientManagement.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public PatientEntity createPatientDetails(PatientEntity patientEntity) {
		return patientRepository.save(patientEntity);
	}

	@Override
	public PatientEntity updatePatientDetails(PatientEntity patientEntity) {
		return patientRepository.save(patientEntity);
	}

	@Override
	public Optional<PatientEntity> getPatientDetailsById(Long patientId) {
		return patientRepository.findById(patientId);
	}

	@Override
	public PatientEntity getPatientDetailsByGovtId(String patientGovtId) {
		return patientRepository.findByGovtIdIgnoreCase(patientGovtId);
	}

	@Override
	public List<PatientEntity> getPatientDetailsByName(String patientName) {
		return patientRepository.findByNameContainingIgnoreCase(patientName);
	}

	@Override
	public void deletePatientDetails(Long patientId) {
		patientRepository.deleteById(patientId);
	}

}
