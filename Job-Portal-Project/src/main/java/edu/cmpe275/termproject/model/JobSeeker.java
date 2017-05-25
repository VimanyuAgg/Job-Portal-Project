package edu.cmpe275.termproject.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="JOB_SEEKER")
public class JobSeeker {
	
	public JobSeeker(){
		
	}
	
	public JobSeeker(String firstName, String lastName, String picture, String selfIntroduction,
			String workExperience, String education, String skills, String username, 
			String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.selfIntroduction = selfIntroduction;
		this.workExperience = workExperience;
		this.education = education;
		this.skills = skills;
		this.username = username;
		this.email = email;
		this.password = password;	
		this.isVerified = false;
	}
	
	@Id
	@Column(name="JSID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long jsid;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="PICTURE")
	private String picture;
	
	@Column(name="SELF_INTRO", length= 3000, nullable=true)
	private String selfIntroduction;
	
	@Column(name="WRK_EXP")
	private String workExperience;
	
	@Column(name="EDU")
	private String education;
	
	@Column(name="SKILLS", length= 3000)
	private String skills;
	
	@Column(name="USER_NAME", unique=true)
	private String username;
	
	@Column(name="EMAIL", unique=true)
	private String email;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="VER_STATUS", nullable=true)
	private boolean isVerified = false;
	
	@Column(name="AUTH_CODE",nullable=true)
	private String authenticationCode = "";
	
	@OneToMany(targetEntity=JobApplication.class, cascade=CascadeType.ALL)
	private List<JobApplication> applicationsList = new ArrayList<JobApplication>();

	@OneToMany
	@JoinTable(name = "JOBSEEKER_INTERESTEDJOB", 
			joinColumns={@JoinColumn(name="personId", referencedColumnName="JSID")}, 
			   inverseJoinColumns = { @JoinColumn(name="jobId",referencedColumnName="JOB_ID") })

	private List<JobPosting> interestedList = new ArrayList<JobPosting>();
	
	
	
	public List<JobPosting> getInterestedList() {
		return interestedList;
	}

	public void setInterestedList(List<JobPosting> interestedList) {
		this.interestedList = interestedList;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified() {
		this.isVerified = true;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setAuthenticationCode(String authenticationCode_String) {
		this.authenticationCode = authenticationCode_String;
	}
	
	public String getAuthenticationCode(){
		return this.authenticationCode;
	}

	public List<JobApplication> getApplicationsList() {
		return applicationsList;
	}

	public void setApplicationsList(List<JobApplication> applicationsList) {
		this.applicationsList = applicationsList;
	}
}
