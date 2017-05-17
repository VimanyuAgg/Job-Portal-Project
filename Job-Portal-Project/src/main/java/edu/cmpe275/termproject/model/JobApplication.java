package edu.cmpe275.termproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOBAPPLICATION")
public class JobApplication {
	
	public JobApplication(){
		
	}
	
	public JobApplication(String status, JobPosting job, JobSeeker applicant,
			byte[] resume, String profile){
		this.jobPosting = job;
		this.applicant = applicant;
		this.status = status;
		this.resume = resume;
		this.profile = profile;
	}
	
	@Id
	@Column(name="APPLICATION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@Column(name="APPLICATION_STATUS")
	private String status;
	
	@OneToOne(targetEntity=JobSeeker.class, cascade=CascadeType.ALL)
	private JobSeeker applicant;

	@Column(name="APPLICATION_POSTED_ON")
	private String postedOn;
	
	@Column(name="PROFILE")
	private String profile;
	
	@OneToOne(targetEntity=JobPosting.class, cascade=CascadeType.ALL)
	private JobPosting jobPosting;

	@Lob
	private byte[] resume;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JobSeeker getApplicant() {
		return applicant;
	}

	public void setApplicant(JobSeeker applicant) {
		this.applicant = applicant;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public JobPosting getJobPosting() {
		return jobPosting;
	}

	public void setJobPosting(JobPosting jobPosting) {
		this.jobPosting = jobPosting;
	}	
}
