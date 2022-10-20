package com.devAcademy.patientManagement.entity;

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
import jakarta.validation.constraints.NotBlank;

/**
 * PatientEntity
 */

@Entity
@Table(name = "patient")
public class PatientEntity {

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	
	@Column(name = "date_of_birth")
	private String dateOfBirth;

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
	public PatientEntity(Long id, String name, String dateOfBirth, List<Long> telephoneNumber, List<GovtIdEntity> govtIds,
			List<AddressEntity> address) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.telephoneNumber = telephoneNumber;
		this.govtIds = govtIds;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the telephoneNumber
	 */
	public List<Long> getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
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

	/**
	 * @return the address
	 */
	public List<AddressEntity> getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(List<AddressEntity> address) {
		this.address = address;
	}
}
