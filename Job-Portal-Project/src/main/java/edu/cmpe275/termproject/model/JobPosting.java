package edu.cmpe275.termproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobPosting {

	@Id
	@Column(name="JOB_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long jobId;
	
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

	public JobPosting(long jobId, String jobDescription, String jobTitle, String jobResponsibilities,
			String jobLocation, String jobSalary) {
		super();
		this.jobId = jobId;
		this.jobDescription = jobDescription;
		this.jobTitle = jobTitle;
		this.jobResponsibilities = jobResponsibilities;
		this.jobLocation = jobLocation;
		this.jobSalary = jobSalary;
	}

	public JobPosting() {
		super();
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
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
	
	
}
