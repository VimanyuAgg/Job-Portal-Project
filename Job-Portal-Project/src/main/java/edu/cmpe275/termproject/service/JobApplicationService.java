package edu.cmpe275.termproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.dao.JobApplicationDAO;
import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.dao.JobSeekerDAO;
import edu.cmpe275.termproject.emailService.JobAppliedEmail;
import edu.cmpe275.termproject.emailService.RegistrationEmail;
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

		System.out.println("inside applyJob ");
		System.out.println("JobSeekerEmail"+jobSeekerEmail);
		JobSeeker applicant = jobSeekerDAO.findByEmail(jobSeekerEmail);
		JobPosting job = jobPostingDAO.findByJobId(jobId);
		System.out.println("line 34");
		try{
			System.out.println("line 36");

			List<JobApplication> applications = applicant.getApplicationsList();
			System.out.println("Line 39");
			// Make sure user has not applied in this job before
			// and state is not in terminal. req #7.f and #7.e
			int count  = 0;
			if(applications!=null){
				for(JobApplication application : applications){
					if(application.getJobPosting().getJobId().equals(job.getJobId())){
						if(application.getStatus().equals("Pending") || 
								application.getStatus().equals("Offered")){
							return "already-applied";
						}
					}
					if(application.getStatus().equals("Pending")) {
						System.out.println("Application Status Pending ");
						count++;
					}
				}
			}
			if(count > 5){
				return "already-applied-in-5";				
			}
			System.out.println("line 60");

			JobApplication jobApplication = new JobApplication("Pending", job, applicant, by, profile);
			jobApplicationDAO.save(jobApplication);
			
			System.out.println("line 61");
			applicant.getApplicationsList().add(jobApplication);
			jobSeekerDAO.save(applicant);

			System.out.println("line 65");

			job.getApplicants().add(jobApplication);
			jobPostingDAO.save(job);
			//JOB application applied email - 
			
			JobAppliedEmail.EmailTrigger(jobSeekerEmail, applicant.getFirstName(),applicant.getLastName(),
										 job.getJobTitle(),job.getJobPostedByCompany().getCompanyName(),jobId);
			
			System.out.println("size "+job.getApplicants().size());
			return "success";
		}catch(Exception e){
			System.out.println("Exception in applyJob()");
		}
		
		return "error";
	}

	public List<JobApplication> findApplicants(String jobId) {
		
		System.out.println("insde findApplicants()");
		JobPosting job = jobPostingDAO.findByJobId(jobId);
		if(job != null) System.out.println("job is not null");
		
		System.out.println("getEligibility is "+job.getEligibility());
		System.out.println("getJobDescription is "+job.getJobDescription());
		System.out.println("jobId is "+job.getJobId());
		System.out.println("jobId is "+job.getJobLocation());
		System.out.println("jobId is "+job.getJobResponsibilities());
		System.out.println("getApplicants size is "+job.getApplicants().size());
		System.out.println("getEligibility is "+job.getEligibility());
		
		for(JobApplication applicant : job.getApplicants()){
			
			System.out.println("insde loop");
			System.out.println(applicant.getId());
			System.out.println(applicant.getPostedOn());
			System.out.println(applicant.getStatus());
		}
		
		return job.getApplicants();
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

	public void updateApplications(String applicationIds, String action){
		
		String ids[] = applicationIds.split(",");
		
		for(String id : ids){
			JobApplication application = jobApplicationDAO.findOne(Long.parseLong(id));
			String status = application.getStatus();
			
			if(action.equals("Cancel") && status.equals("Pending")){
				application.setStatus("Cancelled");
				jobApplicationDAO.save(application);
			}

			else if(action.equals("Reject") && status.equals("Offered")){
				application.setStatus("OfferRejected");
				jobApplicationDAO.save(application);			
			}
			else continue;
		}
	}
}