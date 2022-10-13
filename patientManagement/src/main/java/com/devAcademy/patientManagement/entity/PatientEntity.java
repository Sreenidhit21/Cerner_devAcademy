package com.devAcademy.patientManagement.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class PatientEntity {

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "telephone_number")
	private List<Long> telephoneNumber;

	@OneToMany(targetEntity = GovtIdEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
	private List<GovtIdEntity> govtIds;

	@OneToMany(targetEntity = AddressEntity.class, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
	private List<AddressEntity> address;

	public PatientEntity() {

	}

	/**
	 * @param id
	 * @param name
	 * @param dateOfBirth
	 * @param telephoneNumber
	 * @param govtIds
	 * @param address
	 */
	public PatientEntity(Long id, String name, Date dateOfBirth, List<Long> telephoneNumber, List<GovtIdEntity> govtIds,
			List<AddressEntity> address) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.telephoneNumber = telephoneNumber;
		this.govtIds = govtIds;
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

	public List<Long> getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(List<Long> telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the govtIds
	 */
	public List<GovtIdEntity> getGovtIds() {
		return govtIds;
	}

	/**
	 * @param govtIds the govtIds to set
	 */
	public void setGovtIds(List<GovtIdEntity> govtIds) {
		this.govtIds = govtIds;
	}

	public List<AddressEntity> getAddress() {
		return address;
	}

	public void setAddress(List<AddressEntity> address) {
		this.address = address;
	}

}
