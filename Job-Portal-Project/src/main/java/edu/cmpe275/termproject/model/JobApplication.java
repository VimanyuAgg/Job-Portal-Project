package edu.cmpe275.termproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOBAPPLICATION")
public class JobApplication {
	
	public JobApplication(){
		
	}
	
	@Id
	@Column(name="APPLICATION_ID")
	private String id;
	
	@Column(name="APPLICATION_STATUS")
	private String status;
	
	@OneToMany(targetEntity=JobSeeker.class, cascade=CascadeType.ALL)
	private JobSeeker applicants;

	@Column(name="APPLICATION_POSTED_ON")
	private String postedOn;
	
	@OneToOne(targetEntity=JobPosting.class, cascade=CascadeType.ALL)
	private String JobPosting;
	
	
}
