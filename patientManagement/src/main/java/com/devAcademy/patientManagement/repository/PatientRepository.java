package com.devAcademy.patientManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devAcademy.patientManagement.entity.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
	
	@Query(value = "select p.patient_id,p.date_of_birth,p.name,p.telephone_number from patient p join govt_id g on g.id=:govtid and p.patient_id=g.patient_id", nativeQuery = true)
	public PatientEntity findByGovtIdIgnoreCase(@Param("govtid") String govtId);

	public List<PatientEntity> findByNameContainingIgnoreCase(String Name);

}
