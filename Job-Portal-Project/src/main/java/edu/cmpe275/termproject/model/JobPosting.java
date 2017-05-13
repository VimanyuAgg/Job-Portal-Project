package edu.cmpe275.termproject.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOB_POSTING")
public class JobPosting {

<<<<<<< HEAD
	public JobPosting(long jobId,String jobDescription, String jobTitle, String jobResponsibilities, String jobLocation,
			String jobSalary, Company jobPostedByCompany, String eligibility) {
		super();
		this.jobId=jobId;
=======
	public JobPosting(Long jobId, String jobDescription, String jobTitle, 
			String jobResponsibilities, String jobLocation,
			String jobSalary, Company jobPostedByCompany, 
			String eligibility) {
		
		super();
		this.jobId = jobId;
>>>>>>> 59897ede07aa3eb740c246a8b6b2668cfbcdf685
		this.jobDescription = jobDescription;
		this.jobTitle = jobTitle;
		this.jobResponsibilities = jobResponsibilities;
		this.jobLocation = jobLocation;
		this.jobSalary = jobSalary;
		this.jobPostedByCompany = jobPostedByCompany;
		this.setJobStatus("open");
		this.eligibility=eligibility;
		postedOn = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}

	public Company getJobPostedByCompany() {
		return jobPostedByCompany;
	}

	public void setJobPostedByCompany(Company jobPostedByCompany) {
		this.jobPostedByCompany = jobPostedByCompany;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	@Id
	@Column(name="JOB_ID", unique=true)
	private Long jobId;
	
	@Column(name="JOB_DESC")
	private String jobDescription;
	
	@Column(name="JOB_TITLE")
	private String jobTitle;
	
	@Column(name="JOB_RESP")
	private String jobResponsibilities;
	
	@Column(name="JOB_LCTN")
	private String jobLocation;
	
	@Column(name="JOB_SAL")
	private String jobSalary;

	@ManyToOne
	@JoinColumn(name="companyId")
	private Company jobPostedByCompany;
	
	@Column(name="STATUS")
	private String jobStatus;
	
	@Column(name="POSTED_ON")
	private String postedOn;
	

	@Column(name="ELIGIBILITY")
	private String eligibility;
	
	
//	public JobPosting(long jobId, String jobDescription, String jobTitle, String jobResponsibilities,
//			String jobLocation, String jobSalary, Company jobPostedByCompany) {
//		super();
//		this.jobId = jobId;
//		this.jobDescription = jobDescription;
//		this.jobTitle = jobTitle;
//		this.jobResponsibilities = jobResponsibilities;
//		this.jobLocation = jobLocation;
//		this.jobSalary = jobSalary;
//		this.jobPostedByCompany = jobPostedByCompany;
//		setJobStatus("open");
//	}
	
	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public JobPosting() {
		super();
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobResponsibilities() {
		return jobResponsibilities;
	}

	public void setJobResponsibilities(String jobResponsibilities) {
		this.jobResponsibilities = jobResponsibilities;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	
}
