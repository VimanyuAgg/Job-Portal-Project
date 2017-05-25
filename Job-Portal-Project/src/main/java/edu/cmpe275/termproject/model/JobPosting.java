package edu.cmpe275.termproject.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="JOB_POSTING")
public class JobPosting {

	public JobPosting(String jobId, String jobTitle, String jobDescription, 
			String jobResponsibilities, String jobLocation,
			String jobSalary, Company jobPostedByCompany, 
			String eligibility) throws ParseException {
		
		super();
		this.jobId = jobId;
		this.jobDescription = jobDescription;
		this.jobTitle = jobTitle;
		this.jobResponsibilities = jobResponsibilities;
		this.jobLocation = jobLocation;
		this.jobSalary = jobSalary;
		this.jobPostedByCompany = jobPostedByCompany;
		this.setJobStatus("open");
		this.eligibility=eligibility;
		this.tempSize = 1;
		postedOn = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
		this.sal = Integer.parseInt(jobSalary);
//		this.applications = new ArrayList<JobApplication>();
	}

	

	public int getSal() {
		return sal;
	}



	public void setSal(int salary) {
		this.sal = salary;
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
	private String jobId;
	
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
	
	@Column (name="SAL")
	private int sal;

	@ManyToOne
	@JoinColumn(name="companyId")
	private Company jobPostedByCompany;
	
	@Column(name="STATUS")
	private String jobStatus;
	
	@Column(name="POSTED_ON")
	private String postedOn;
	
	@OneToMany(targetEntity=JobApplication.class, cascade=CascadeType.ALL)
	private List<JobApplication> applications = new ArrayList<JobApplication>();
	
	@OneToMany
	@JoinTable(
			name="JOBPOST_INT_APPLICANTS",
			joinColumns={@JoinColumn(name="JOB_ID", referencedColumnName="JOB_ID")},
			inverseJoinColumns={@JoinColumn(name="INTERESTED_JSID" ,referencedColumnName="JSID")}
			)
	private List<JobSeeker> interestedApplicants = new ArrayList<JobSeeker>();

	

	@Column(name="ELIGIBILITY")
	private String eligibility;
	
	
	@Column(name="TEMPSIZE")
	private int tempSize;

	public JobPosting() {
		super();
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
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

	public int getTempSize() {
		return tempSize;
	}

	public void setTempSize(int tempSize) {
		this.tempSize = tempSize;
	}

	public void setApplicants(JobApplication applicant){
		this.applications.add(applicant);
	}

	public List<JobApplication> getApplicants() {
		return this.applications;
	}
	
	public List<JobSeeker> getInterestedApplicants() {
		return interestedApplicants;
	}



	public void setInterestedApplicants(List<JobSeeker> interestedApplicants) {
		this.interestedApplicants = interestedApplicants;
	}
}