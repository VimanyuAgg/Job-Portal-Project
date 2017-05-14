package edu.cmpe275.termproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOBAPPLICATION")
public class JobApplication {
	
	public JobApplication(){
		
	}
	
	public JobApplication(String status, JobPosting job, JobSeeker applicant){
		this.jobPosting = job;
		this.applicant = applicant;
		this.status = status;
	}
	
	@Id
	@Column(name="APPLICATION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="APPLICATION_STATUS")
	private String status;
	
	@OneToOne(targetEntity=JobSeeker.class, cascade=CascadeType.ALL)
	private JobSeeker applicant;

	@Column(name="APPLICATION_POSTED_ON")
	private String postedOn;
	
	@OneToOne(targetEntity=JobPosting.class, cascade=CascadeType.ALL)
	private JobPosting jobPosting;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public JobPosting getJobPosting() {
		return jobPosting;
	}

	public void setJobPosting(JobPosting jobPosting) {
		this.jobPosting = jobPosting;
	}	
}
