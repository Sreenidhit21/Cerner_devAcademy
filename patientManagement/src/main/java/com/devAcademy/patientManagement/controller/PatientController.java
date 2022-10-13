package com.devAcademy.patientManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/{id}")
	public Optional<PatientEntity> getPatientDetailsById(@PathVariable("id") Long id) {
		return patientService.getPatientDetailsById(id);
	}

	@GetMapping("/govtId/{id}")
	public PatientEntity getPatientDetailsByGovtId(@PathVariable("id") String id) {
		return patientService.getPatientDetailsByGovtId(id);
	}

	@GetMapping("/patientName/{name}")
	public List<PatientEntity> getPatientDetailsByName(@PathVariable("name") String name) {
		return patientService.getPatientDetailsByName(name);
	}

	@DeleteMapping("/{patientId}")
	public void deletePatientDetails(@PathVariable("patientId") Long patientId) {
		patientService.deletePatientDetails(patientId);
	}

	@PostMapping
	public PatientEntity createPatientDetails(@RequestBody PatientEntity patientEntity) {
		return patientService.createPatientDetails(patientEntity);
	}

	@PutMapping
	public PatientEntity updatePatientDetails(@RequestBody PatientEntity patientEntity) {
		return patientService.updatePatientDetails(patientEntity);
	}

}
