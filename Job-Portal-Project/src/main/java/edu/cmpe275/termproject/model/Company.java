package edu.cmpe275.termproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="COMPANY")
public class Company {
	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Company(String companyName, String website, String logoUrl, String address,
			String description, String email, String password, int size, List<JobPosting> jobPostingList) {
		this.companyName = companyName;
		this.website = website;
		this.logoUrl = logoUrl;
		this.address = address;
		this.email=email;
		this.password=password;
		this.description = description;
		this.size=size;
		jobPostingList=null;
	}

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
	
	@Column(name="COMPANY_ADDRESS",length= 3000)
	private String address;
	
	@Column(name="COMPANY_DESC", nullable=true, length= 3000)
	private String description;

	@OneToMany(mappedBy="jobPostedByCompany", cascade=CascadeType.ALL)
	private List<JobPosting> jobPostingList;
	
	@Column(name="EMAIL", unique=true)
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	@Column(name="SIZE")
	private int size;
	
	@Column(name="VER_STATUS", nullable=true)
	private boolean isVerified = false;
	
	@Column(name="AUTH_CODE",nullable=true)
	private String authenticationCode = "";
	
	public boolean isVerified() {
		return isVerified;
	}


	public void setVerified() {
		this.isVerified = true;
	}


	public String getAuthenticationCode() {
		return authenticationCode;
	}


	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<JobPosting> getJobPostingList() {
		return jobPostingList;
	}


	public void setJobPostingList(List<JobPosting> jobPostingList) {
		this.jobPostingList = jobPostingList;
	}

	
	
	public Company(){
		//pass	
		
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
