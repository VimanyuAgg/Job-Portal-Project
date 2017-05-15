package edu.cmpe275.termproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.dao.JobApplicationDAO;
import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.dao.JobSeekerDAO;
import edu.cmpe275.termproject.model.JobApplication;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.model.JobSeeker;

@Service
public class JobApplicationService {

	@Autowired
	private JobSeekerDAO jobSeekerDAO;
	
	@Autowired
	private JobPostingDAO jobPostingDAO;
	
	@Autowired
	private JobApplicationDAO jobApplicationDAO;

	public String applyJob(String jobId, String jobSeekerEmail, byte[] by, String profile) {
		
		JobSeeker applicant = jobSeekerDAO.findByEmail(jobSeekerEmail);
		
		System.out.println("inside applyJob ");
		
		JobPosting job = jobPostingDAO.findByJobId(jobId);
		try{
			JobApplication jobApplication = new JobApplication("Pending", job, applicant, by, profile);
			jobApplicationDAO.save(jobApplication);
			
			applicant.getApplicationsList().add(jobApplication);
			jobSeekerDAO.save(applicant);
			
			job.getApplicants().add(jobApplication);
			jobPostingDAO.save(job);
			
			System.out.println("size "+job.getApplicants().size());
			return "success";
		}catch(Exception e){
			System.out.println("Exception in applyJob()");
		}
		
		return "error";
	}

	public int checkTotalPendingApplications(String email) {
		// TODO Auto-generated method stub
		int count=0;
		List<JobApplication> applications=jobSeekerDAO.findByEmail(email).getApplicationsList();
		for(JobApplication application:applications){
			if(application.getStatus().equals("pending"))
				count++;
		}
		return count;
	}

	public String findApplicants(String jobId) {
		
		System.out.println("insde findApplicants()");
		JobPosting job = jobPostingDAO.findByJobId(jobId);
		if(job != null) System.out.println("job is not null");
		
		System.out.println("getEligibility is "+job.getEligibility());
		System.out.println("getJobDescription is "+job.getJobDescription());
		System.out.println("jobId is "+job.getJobId());
		System.out.println("jobId is "+job.getJobLocation());
		System.out.println("jobId is "+job.getJobResponsibilities());
		System.out.println("getApplicants size is "+job.getApplicants().size());
		System.out.println("jobId is "+job.getEligibility());
		
		for(JobApplication applicant : job.getApplicants()){
			
			System.out.println("insde loop");
			System.out.println(applicant.getId());
			System.out.println(applicant.getPostedOn());
			System.out.println(applicant.getStatus());
		}
		
		return null;
	}

	public boolean checkIfApplicationPending(String jobId, String jobSeekerUsername) {
		// TODO Auto-generated method stub
		JobSeeker applicant= jobSeekerDAO.findByUsername(jobSeekerUsername);
		List<JobApplication> applications=applicant.getApplicationsList();
		for(JobApplication application:applications){
			if(application.getJobPosting().getJobId().equals(jobId)){
				if(application.getStatus().equals("pending"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public List<JobApplication> findApplications(JobSeeker applicant) {
		
		
		
		return (List<JobApplication>) jobApplicationDAO.findByApplicant(applicant);
	}
}