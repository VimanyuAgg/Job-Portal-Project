package edu.cmpe275.termproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="JOB_SEEKER")
public class JobSeeker {
	
	@Id
	@Column(name="JSID")
	private long jsid;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="PICTURE")
	private String picture;
	
	@Column(name="SELF_INTRO", nullable=true)
	private String selfIntroduction;
	
	@Column(name="WRK_EXP")
	private String workExperience;
	
	@Column(name="EDU")
	private String education;
	
	
	@Column(name="SKILLS")
	private String skills;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinTable(
			name="T_JobSeeker_JobPosting",
			joinColumns={@JoinColumn(name="JSID", referencedColumnName="JSID")},
			inverseJoinColumns={@JoinColumn(name="JOB_ID", referencedColumnName="JOB_ID")})
	
	private List<JobPosting> jobPostingList= new ArrayList<JobPosting>();

	
		public JobSeeker()
	{}

	public JobSeeker(long jsid, String firstName, String lastName, String selfIntroduction, String workExperience,
			String education, String skills, List< JobPosting > jobPostingList) {
		super();
		this.jsid = jsid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.selfIntroduction = selfIntroduction;
		this.workExperience = workExperience;
		this.education = education;
		this.skills = skills;
		this.jobPostingList = jobPostingList;
	}

	public long getJsid() {
		return jsid;
	}

	public void setJsid(long jsid) {
		this.jsid = jsid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public List<JobPosting> getJobPostingList() {
		return jobPostingList;
	}

	public void setJobPostingList(List<JobPosting> jobPostingList) {
		this.jobPostingList = jobPostingList;
	}
	
	
	

	
	
	
	
}
