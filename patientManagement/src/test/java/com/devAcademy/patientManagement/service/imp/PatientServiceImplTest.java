/**
 * 
 */
package com.devAcademy.patientManagement.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.devAcademy.patientManagement.repository.PatientRepository;
import com.devAcademy.patientManagement.service.impl.PatientServiceImpl;

/**
 * @author ST105714
 *
 */
class PatientServiceImplTest {

	@InjectMocks
	PatientServiceImpl patientServiceImpl;

	@Mock
	PatientRepository patientRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.service.impl.PatientServiceImpl#createPatientDetails(com.devAcademy.patientManagement.entity.PatientEntity)}.
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

		when(patientRepository.save(any())).thenReturn(patientEntity);

		PatientEntity entity = patientServiceImpl.createPatientDetails(patientEntity);
		assertEquals("preethi", entity.getName());
	}

	@Test
	void testCreatePatientDetails_whenReasonForNotSharingGovtIdGiven()
			throws GovtIdOrReasonForNotSharingRequiredException {
		GovtIdEntity govtIdEntity = new GovtIdEntity(2345l, null, null, "no id created");
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(456l, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		PatientEntity patientEntity = new PatientEntity(345l, "preethi", "1984 - 01 - 05", phone,
				govtIdEntities, addressEntities);

		when(patientRepository.save(any())).thenReturn(patientEntity);

		PatientEntity entity = patientServiceImpl.createPatientDetails(patientEntity);
		assertEquals("preethi", entity.getName());
	}

	@Test
	void testCreatePatientDetails_GovtIdOrReasonForNotSharingRequiredException() {
		GovtIdEntity govtIdEntity = new GovtIdEntity(null, null, "DL", null);
		List<GovtIdEntity> govtIdEntities = new ArrayList<>();
		govtIdEntities.add(govtIdEntity);
		List<AddressEntity> addressEntities = new ArrayList<>();
		AddressEntity addressEntity = new AddressEntity(null, "456", "bangalore", "Karnataka", null, null);
		addressEntities.add(addressEntity);
		List<Long> phone = new ArrayList<>();
		phone.add(3456783489l);
		PatientEntity patientEntity = new PatientEntity(null, "preethi", "1984 - 01 - 05", phone,
				govtIdEntities, addressEntities);

		assertThrows(GovtIdOrReasonForNotSharingRequiredException.class, () -> {
			patientServiceImpl.createPatientDetails(patientEntity);
		});
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.service.impl.PatientServiceImpl#updatePatientDetails(com.devAcademy.patientManagement.entity.PatientEntity)}.
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

		when(patientRepository.save(any())).thenReturn(patientEntity);

		PatientEntity entity = patientServiceImpl.updatePatientDetails(patientEntity);
		assertEquals("preethi", entity.getName());
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.service.impl.PatientServiceImpl#getPatientDetailsById(java.lang.Long)}.
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

		when(patientRepository.findById(anyLong())).thenReturn(patientEntity);

		Optional<PatientEntity> entity = patientServiceImpl.getPatientDetailsById(345l);
		assertEquals("preethi", entity.get().getName());
	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.service.impl.PatientServiceImpl#getPatientDetailsByGovtId(java.lang.String)}.
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

		when(patientRepository.findByGovtIdIgnoreCase(anyString())).thenReturn(patientEntity);

		PatientEntity entity = patientServiceImpl.getPatientDetailsByGovtId("DL 123");
		assertEquals("preethi", entity.getName());

	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.service.impl.PatientServiceImpl#getPatientDetailsByName(java.lang.String)}.
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

		when(patientRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(entities);

		List<PatientEntity> entity = patientServiceImpl.getPatientDetailsByName("preethi");
		assertEquals("preethi", entity.get(0).getName());

	}

	@Test
	void testGetPatientDetailsByName_PatientNotFoundException() {

		when(patientRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(null);

		assertThrows(PatientNotFoundException.class, () -> {
			patientServiceImpl.getPatientDetailsByName("preethi");
		});

	}

	@Test
	void testGetPatientDetailsById_PatientNotFoundException() {

		when(patientRepository.findById(anyLong())).thenReturn(null);

		assertThrows(PatientNotFoundException.class, () -> {
			patientServiceImpl.getPatientDetailsById(123l);
		});

	}

	@Test
	void testGetPatientDetailsByGovtId_PatientNotFoundException() {

		when(patientRepository.findByGovtIdIgnoreCase(anyString())).thenReturn(null);

		assertThrows(PatientNotFoundException.class, () -> {
			patientServiceImpl.getPatientDetailsByGovtId("Dl 789");
		});

	}

	/**
	 * Test method for
	 * {@link com.devAcademy.patientManagement.service.impl.PatientServiceImpl#deletePatientDetails(java.lang.Long)}.
	 */
	@Test
	void testDeletePatientDetails() {

		doNothing().when(patientRepository).deleteById(anyLong());

		patientServiceImpl.deletePatientDetails(1234l);
		verify(patientRepository, times(1)).deleteById(1234l);
	}

}
