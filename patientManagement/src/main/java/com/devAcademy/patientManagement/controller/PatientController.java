package com.devAcademy.patientManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.exception.GovtIdOrReasonForNotSharingRequiredException;
import com.devAcademy.patientManagement.exception.PatientNotFoundException;
import com.devAcademy.patientManagement.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	/**
	 * @param id
	 * @return Optional<PatientEntity>
	 * @throws PatientNotFoundException
	 *                                  <p>
	 *                                  getPatientDetailsById
	 *                                  </p>
	 */
	@GetMapping("/{id}")
	public Optional<PatientEntity> getPatientDetailsById(@PathVariable("id") Long id) throws PatientNotFoundException {
		return patientService.getPatientDetailsById(id);
	}

	/**
	 * @param id
	 * @return PatientEntity
	 * @throws PatientNotFoundException
	 *                                  <p>
	 *                                  getPatientDetailsByGovtId
	 *                                  </p>
	 */
	@GetMapping("/govtId/{id}")
	public PatientEntity getPatientDetailsByGovtId(@PathVariable("id") String id) throws PatientNotFoundException {
		return patientService.getPatientDetailsByGovtId(id);
	}

	/**
	 * @param name
	 * @return PatientEntity
	 * @throws PatientNotFoundException
	 *                                  <p>
	 *                                  getPatientDetailsByName
	 *                                  </p>
	 */
	@GetMapping("/patientName/{name}")
	public List<PatientEntity> getPatientDetailsByName(@PathVariable("name") String name)
			throws PatientNotFoundException {
		return patientService.getPatientDetailsByName(name);
	}

	/**
	 * @param patientId
	 * @return void
	 *         <p>
	 *         deletePatientDetails
	 *         </p>
	 */
	@DeleteMapping("/{patientId}")
	public void deletePatientDetails(@PathVariable("patientId") Long patientId) {
		patientService.deletePatientDetails(patientId);
	}

	/**
	 * @param patientEntity
	 * @return PatientEntity
	 * @throws GovtIdOrReasonForNotSharingRequiredException
	 *                                                      <p>
	 *                                                      createPatientDetails
	 *                                                      </p>
	 */
	@PostMapping
	public PatientEntity createPatientDetails(@RequestBody @Validated PatientEntity patientEntity)
			throws GovtIdOrReasonForNotSharingRequiredException {
		return patientService.createPatientDetails(patientEntity);
	}

	/**
	 * @param patientEntity
	 * @return PatientEntity
	 *         <p>
	 *         updatePatientDetails
	 *         </p>
	 */
	@PutMapping
	public PatientEntity updatePatientDetails(@RequestBody PatientEntity patientEntity) {
		return patientService.updatePatientDetails(patientEntity);
	}

}
