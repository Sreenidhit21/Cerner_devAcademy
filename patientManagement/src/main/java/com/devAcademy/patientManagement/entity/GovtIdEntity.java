package com.devAcademy.patientManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="govtId")
public class GovtIdEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "govt_id")
	private String govtId;
	
	@Column(name ="govt_id_type")
	private String govtIdType;
	
	@Column(name = "reason_for_not_sharing_id")
	private String reasonForNotSharingId;

	public GovtIdEntity() {
	}

	
	/**
	 * @param id
	 * @param govtId
	 * @param govtIdType
	 * @param reasonForNotSharingId
	 */
	public GovtIdEntity(Long id, String govtId, String govtIdType, String reasonForNotSharingId) {
		super();
		this.id = id;
		this.govtId = govtId;
		this.govtIdType = govtIdType;
		this.reasonForNotSharingId = reasonForNotSharingId;
	}


	/**
	 * @return the govtId
	 */
	public String getGovtId() {
		return govtId;
	}

	/**
	 * @param govtId the govtId to set
	 */
	public void setGovtId(String govtId) {
		this.govtId = govtId;
	}

	/**
	 * @return the govtIdType
	 */
	public String getGovtIdType() {
		return govtIdType;
	}

	/**
	 * @param govtIdType the govtIdType to set
	 */
	public void setGovtIdType(String govtIdType) {
		this.govtIdType = govtIdType;
	}

	/**
	 * @return the reasonForNotSharingId
	 */
	public String getReasonForNotSharingId() {
		return reasonForNotSharingId;
	}

	/**
	 * @param reasonForNotSharingId the reasonForNotSharingId to set
	 */
	public void setReasonForNotSharingId(String reasonForNotSharingId) {
		this.reasonForNotSharingId = reasonForNotSharingId;
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
	
	

}
