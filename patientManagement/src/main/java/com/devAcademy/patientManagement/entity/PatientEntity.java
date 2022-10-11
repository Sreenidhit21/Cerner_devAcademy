package com.devAcademy.patientManagement.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class PatientEntity {
	@Column(name = "patient_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "govt_id")
	private Long govtId;
	
	@Column(name = "reason_for_not_sharing_id")
	private String reasonForNotSharingId;
	
	@Column(name = "telephone_number")
	private List<Long> telephoneNumber;
	
	@Column(name = "address")
	@OneToMany(targetEntity=AddressEntity.class, mappedBy="patient", fetch=FetchType.EAGER)
	//@OneToMany(mappedBy = )
	private List<AddressEntity> address;
	
	public PatientEntity() {
		
	}
	
	public PatientEntity(Long id, String name, Date dateOfBirth, Long govtId, String reasonForNotSharingId,
			List<Long> telephoneNumber, List<AddressEntity> address) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.govtId = govtId;
		this.reasonForNotSharingId = reasonForNotSharingId;
		this.telephoneNumber = telephoneNumber;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getGovtId() {
		return govtId;
	}

	public void setGovtId(Long govtId) {
		this.govtId = govtId;
	}

	public String getReasonForNotSharingId() {
		return reasonForNotSharingId;
	}

	public void setReasonForNotSharingId(String reasonForNotSharingId) {
		this.reasonForNotSharingId = reasonForNotSharingId;
	}

	public List<Long> getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(List<Long> telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public List<AddressEntity> getAddress() {
		return address;
	}

	public void setAddress(List<AddressEntity> address) {
		this.address = address;
	}
	
	
	


}
