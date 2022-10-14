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

/**
 * PatientServiceImpl
 */

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	/**
	 * @param patientEntity
	 * @return PatientEntity
	 *         <p>
	 *         createPatientDetails
	 *         </p>
	 */
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

	/**
	 * @param patientEntity
	 * @return PatientEntity
	 *         <p>
	 *         updatePatientDetails
	 *         </p>
	 */
	@Override
	public PatientEntity updatePatientDetails(PatientEntity patientEntity) {
		return patientRepository.save(patientEntity);
	}

	/**
	 * @param patientId
	 * @return Optional<PatientEntity>
	 *         <p>
	 *         getPatientDetailsById
	 *         </p>
	 */
	@Override
	public Optional<PatientEntity> getPatientDetailsById(Long patientId) throws PatientNotFoundException {
		Optional<PatientEntity> patientEntity = patientRepository.findById(patientId);
		if (patientEntity == null || patientEntity.isEmpty()) {
			throw new PatientNotFoundException("Patient not found with Id :" + patientId);
		} else {
			return patientEntity;
		}
	}

	/**
	 * @param patientGovtId
	 * @return PatientEntity
	 *         <p>
	 *         getPatientDetailsByGovtId
	 *         </p>
	 */
	@Override
	public PatientEntity getPatientDetailsByGovtId(String patientGovtId) throws PatientNotFoundException {
		PatientEntity patientEntity = patientRepository.findByGovtIdIgnoreCase(patientGovtId);
		if (patientEntity == null) {
			throw new PatientNotFoundException("Patient not found with Govt ID : " + patientGovtId);
		} else {
			return patientEntity;
		}
	}

	/**
	 * @param patientName
	 * @return List<PatientEntity>
	 *         <p>
	 *         getPatientDetailsByName
	 *         </p>
	 */
	@Override
	public List<PatientEntity> getPatientDetailsByName(String patientName) throws PatientNotFoundException {
		List<PatientEntity> patientList = patientRepository.findByNameContainingIgnoreCase(patientName);
		if (patientList == null || patientList.isEmpty()) {
			throw new PatientNotFoundException("Patient not found with name : " + patientName);
		} else {
			return patientList;
		}
	}

	/**
	 * @param Long patientId
	 *             <p>
	 *             deletePatientDetails
	 *             </p>
	 */
	@Override
	public void deletePatientDetails(Long patientId) {
		patientRepository.deleteById(patientId);
	}

}
