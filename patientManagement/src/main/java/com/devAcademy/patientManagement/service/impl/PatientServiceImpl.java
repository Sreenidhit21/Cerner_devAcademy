package com.devAcademy.patientManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devAcademy.patientManagement.entity.GovtIdEntity;
import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.exception.GovtIdOrReasonForNotSharingRequiredException;
import com.devAcademy.patientManagement.exception.PatientNotFoundException;
import com.devAcademy.patientManagement.repository.PatientRepository;
import com.devAcademy.patientManagement.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public PatientEntity createPatientDetails(PatientEntity patientEntity)
			throws GovtIdOrReasonForNotSharingRequiredException {
		if (patientEntity.getGovtIds() == null || patientEntity.getGovtIds().isEmpty()) {
			throw new GovtIdOrReasonForNotSharingRequiredException("Govt ID or Reason for not sharing ID is required");
		} else {
			List<GovtIdEntity> govtIdEntities = patientEntity.getGovtIds();
			for (GovtIdEntity govtIdEntity : govtIdEntities) {
				if ((govtIdEntity.getGovtId() == null || govtIdEntity.getGovtId().isBlank())
						&& (govtIdEntity.getReasonForNotSharingId() == null
								|| govtIdEntity.getReasonForNotSharingId().isBlank())) {
					throw new GovtIdOrReasonForNotSharingRequiredException(
							"Govt ID or Reason for not sharing ID is required");
				}
			}
			return patientRepository.save(patientEntity);
		}

	}

	@Override
	public PatientEntity updatePatientDetails(PatientEntity patientEntity) {
		return patientRepository.save(patientEntity);
	}

	@Override
	public Optional<PatientEntity> getPatientDetailsById(Long patientId) throws PatientNotFoundException {
		Optional<PatientEntity> patientEntity = patientRepository.findById(patientId);
		if (patientEntity==null||patientEntity.isEmpty() ) {
			throw new PatientNotFoundException("Patient not found with Id :" + patientId);
		} else {
			return patientEntity;
		}
	}

	@Override
	public PatientEntity getPatientDetailsByGovtId(String patientGovtId) throws PatientNotFoundException {
		PatientEntity patientEntity = patientRepository.findByGovtIdIgnoreCase(patientGovtId);
		if (patientEntity == null) {
			throw new PatientNotFoundException("Patient not found with Govt ID : " + patientGovtId);
		} else {
			return patientEntity;
		}
	}

	@Override
	public List<PatientEntity> getPatientDetailsByName(String patientName) throws PatientNotFoundException {
		List<PatientEntity> patientList = patientRepository.findByNameContainingIgnoreCase(patientName);
		if (patientList == null || patientList.isEmpty()) {
			throw new PatientNotFoundException("Patient not found with name : " + patientName);
		} else {
			return patientList;
		}
	}

	@Override
	public void deletePatientDetails(Long patientId) {
		patientRepository.deleteById(patientId);
	}

}
