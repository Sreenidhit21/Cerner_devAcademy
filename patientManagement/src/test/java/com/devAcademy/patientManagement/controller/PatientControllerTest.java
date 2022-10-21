/**
 * 
 */
package com.devAcademy.patientManagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devAcademy.patientManagement.entity.AddressEntity;
import com.devAcademy.patientManagement.entity.GovtIdEntity;
import com.devAcademy.patientManagement.entity.PatientEntity;
import com.devAcademy.patientManagement.exception.GovtIdOrReasonForNotSharingRequiredException;
import com.devAcademy.patientManagement.exception.PatientNotFoundException;
import com.devAcademy.patientManagement.service.impl.PatientServiceImpl;

/**
 * @author ST105714
 *
 */
class PatientControllerTest {

	@InjectMocks
	PatientController PatientController;

	@Mock
	PatientServiceImpl patientServiceImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.controller.PatientController#getPatientDetailsById(java.lang.Long)}.
	 * 
	 * @throws PatientNotFoundException
	 */
	@Test
	void testGetPatientDetailsById() throws PatientNotFoundException {
		GovtIdEntity govtIdEntity = new GovtIdEntity(2345l, "DL 123", "DL", null);
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(456l, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		Optional<PatientEntity> patientEntity = Optional.ofNullable(
				new PatientEntity(345l, "preethi", "1984 - 01 - 05", phone, govtIdEntities, addressEntities));

		when(patientServiceImpl.getPatientDetailsById(anyLong())).thenReturn(patientEntity);

		Optional<PatientEntity> entity = PatientController.getPatientDetailsById(345l);
		assertEquals("preethi", entity.get().getName());
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.controller.PatientController#getPatientDetailsByGovtId(java.lang.String)}.
	 * 
	 * @throws PatientNotFoundException
	 */
	@Test
	void testGetPatientDetailsByGovtId() throws PatientNotFoundException {
		GovtIdEntity govtIdEntity = new GovtIdEntity(2345l, "DL 123", "DL", null);
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(456l, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		PatientEntity patientEntity = new PatientEntity(345l, "preethi", "1984 - 01 - 05", phone,
				govtIdEntities, addressEntities);

		when(patientServiceImpl.getPatientDetailsByGovtId(anyString())).thenReturn(patientEntity);

		PatientEntity entity = PatientController.getPatientDetailsByGovtId("DL 786");
		assertEquals("preethi", entity.getName());
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.controller.PatientController#getPatientDetailsByName(java.lang.String)}.
	 * 
	 * @throws PatientNotFoundException
	 */
	@Test
	void testGetPatientDetailsByName() throws PatientNotFoundException {
		GovtIdEntity govtIdEntity = new GovtIdEntity(2345l, "DL 123", "DL", null);
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(456l, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		PatientEntity patientEntity = new PatientEntity(345l, "preethi", "1984 - 01 - 05", phone,
				govtIdEntities, addressEntities);
		List<PatientEntity> entities = new ArrayList<>();
		entities.add(patientEntity);

		when(patientServiceImpl.getPatientDetailsByName(anyString())).thenReturn(entities);

		List<PatientEntity> entity = PatientController.getPatientDetailsByName("preethi");
		assertEquals("preethi", entity.get(0).getName());
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.controller.PatientController#deletePatientDetails(java.lang.Long)}.
	 */
	@Test
	void testDeletePatientDetails() {
		doNothing().when(patientServiceImpl).deletePatientDetails(anyLong());

		PatientController.deletePatientDetails(1234l);
		verify(patientServiceImpl, times(1)).deletePatientDetails(1234l);
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.controller.PatientController#createPatientDetails(com.devAcademy.patientManagement.entity.PatientEntity)}.
	 * 
	 * @throws GovtIdOrReasonForNotSharingRequiredException
	 */
	@Test
	void testCreatePatientDetails() throws GovtIdOrReasonForNotSharingRequiredException {
		GovtIdEntity govtIdEntity = new GovtIdEntity(2345l, "DL 123", "DL", null);
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(456l, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		PatientEntity patientEntity = new PatientEntity(345l, "preethi", "1984 - 01 - 05", phone,
				govtIdEntities, addressEntities);

		when(patientServiceImpl.createPatientDetails(any())).thenReturn(patientEntity);

		PatientEntity entity = PatientController.createPatientDetails(patientEntity);
		assertEquals("preethi", entity.getName());
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.controller.PatientController#updatePatientDetails(com.devAcademy.patientManagement.entity.PatientEntity)}.
	 */
	@Test
	void testUpdatePatientDetails() {
		GovtIdEntity govtIdEntity = new GovtIdEntity(2345l, "DL 123", "DL", null);
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(456l, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		PatientEntity patientEntity = new PatientEntity(345l, "preethi", "1984 - 01 - 05", phone,
				govtIdEntities, addressEntities);

		when(patientServiceImpl.updatePatientDetails(any())).thenReturn(patientEntity);

		PatientEntity entity = PatientController.updatePatientDetails(patientEntity);
		assertEquals("preethi", entity.getName());
	}

}
