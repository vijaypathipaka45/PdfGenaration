package com.infy.dto;

public class AMSDto {
	
	private String AMSNo;
	
	private String description;
	
	private String threesholdInterval;
	
	private String sorceCode;
	
	private String reference;
	
	private String effectivity;
	
	private String FEC;
	
	private String revisionReference;

	public String getAMSNo() {
		return AMSNo;
	}

	public void setAMSNo(String aMSNo) {
		AMSNo = aMSNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThreesholdInterval() {
		return threesholdInterval;
	}

	public void setThreesholdInterval(String threesholdInterval) {
		this.threesholdInterval = threesholdInterval;
	}

	public String getSorceCode() {
		return sorceCode;
	}

	public void setSorceCode(String sorceCode) {
		this.sorceCode = sorceCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEffectivity() {
		return effectivity;
	}

	public void setEffectivity(String effectivity) {
		this.effectivity = effectivity;
	}

	public String getFEC() {
		return FEC;
	}

	public void setFEC(String fEC) {
		FEC = fEC;
	}

	public String getRevisionReference() {
		return revisionReference;
	}

	public void setRevisionReference(String revisionReference) {
		this.revisionReference = revisionReference;
	}

	@Override
	public String toString() {
		return "AMSDto [AMSNo=" + AMSNo + ", description=" + description + ", threesholdInterval=" + threesholdInterval
				+ ", sorceCode=" + sorceCode + ", reference=" + reference + ", effectivity=" + effectivity + ", FEC="
				+ FEC + ", revisionReference=" + revisionReference + "]";
	}
	
	

}
