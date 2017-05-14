package edu.cmpe275.termproject.service;

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

	public String applyJob(String jobId, String jobSeekerUsername) {
		
		JobSeeker applicant = jobSeekerDAO.findByUsername(jobSeekerUsername);
		
		JobPosting job = jobPostingDAO.findByJobId(jobId);
		
		try{
			
			JobApplication jobApplication = new JobApplication("Pending", job, applicant);
			jobApplicationDAO.save(jobApplication);
			
			applicant.getJobPostingList().add(job);
			
			job.getApplicants().add(jobApplication);
			
		}catch(Exception e){
			
		}
		
		
		return null;
	}
}