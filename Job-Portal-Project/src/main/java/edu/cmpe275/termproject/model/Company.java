package edu.cmpe275.termproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
	@Column(name="COMPANY_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long companyId;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="COMPANY_WEBSITE")
	private String website;
	
	@Column(name="COMPANY_LOGO_URL", nullable=true)
	private String logoUrl;
	
	@Column(name="COMPANY_ADDRESS")
	private String address;
	
	@Column(name="COMPANY_DESC", nullable=true)
	private String description;

	
	public Company(){
		//pass	
		
	}
	
	
	public Company(long companyId, String companyName, String website, String logoUrl, String address,
			String description) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.website = website;
		this.logoUrl = logoUrl;
		this.address = address;
		this.description = description;
	}


	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
