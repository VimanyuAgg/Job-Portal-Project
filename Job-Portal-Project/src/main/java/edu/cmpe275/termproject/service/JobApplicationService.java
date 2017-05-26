package edu.cmpe275.termproject.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationSessionCookieConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.dao.JobApplicationDAO;
import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.dao.JobSeekerDAO;
import edu.cmpe275.termproject.emailService.CongratulatoryEmail;
import edu.cmpe275.termproject.emailService.JobApplicationCancelEmail;
import edu.cmpe275.termproject.emailService.JobAppliedEmail;
import edu.cmpe275.termproject.emailService.RegistrationEmail;
import edu.cmpe275.termproject.model.Company;
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
			
			for(int i=0;i<applicant.getInterestedList().size();i++){
				if(applicant.getInterestedList().get(i).getJobId().equals(jobId)){
					System.out.println("job id exists in interested jobs list");
					applicant.getInterestedList().remove(i);
					System.out.println("removed the job posting from applicant's interested jobs list");
				}
			}
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

		System.out.println("inside findApplications");

		List<JobApplication> jobApplications = (List<JobApplication>) jobApplicationDAO.findAll();
		List<JobApplication> result = new ArrayList();
		
		for(JobApplication application : jobApplications){
			System.out.println("inside loop");
			System.out.println("inside loop list "+application.getApplicant().getJsid());
			System.out.println("inside loop applicant "+applicant.getJsid());
			if(application.getApplicant().getJsid() == applicant.getJsid()){
				System.out.println("inside if, applicant found");
				result.add(application);
			}
			
		}
		
		return result;
	}

	public void updateApplications(String applicationIds, String action){
		System.out.println("Line 159 in Update Applications");
		String ids[] = applicationIds.split(",");
		System.out.println("Applciation IDs::"+applicationIds);
		for(String id : ids){
			System.out.println("line 163::"+id);
			JobApplication application = jobApplicationDAO.findOne(id);
			if(application != null) System.out.println("found job application");
			else System.out.println("not found job application");
			
			String status = application.getStatus();
			
			if(action.equals("Cancel") && status.equals("Pending")){
				System.out.println("inside Pending");
				application.setStatus("Cancelled");
				jobApplicationDAO.save(application);
			}

			else if(action.equals("Reject") && status.equals("Offered")){
				System.out.println("inside Offered");
				application.setStatus("OfferRejected");
				jobApplicationDAO.save(application);			
			}
			
			else if(action.equals("Accept") && status.equals("Offered")){
				System.out.println("inside OfferAccepted");
				application.setStatus("OfferAccpeted");
				jobApplicationDAO.save(application);
				
				// mark the field in Company's job as Filled
				application.getJobPosting().setJobStatus("Filled");
				jobPostingDAO.save(application.getJobPosting());
				
				// email all other applicants that position is filled now
				List<JobApplication> applications = application.getJobPosting().getApplicants();
				System.out.println("size of applications is "+applications.size());
			
				for(int i=0;i<applications.size();i++){
					//Sending position filled email
					if(applications.get(i).getApplicant().getJsid() != application.getApplicant().getJsid()){
						JobApplicationCancelEmail.jobFilledEmail(applications.get(i).getApplicant().getEmail(),
								applications.get(i).getApplicant().getFirstName(),
								applications.get(i).getApplicant().getLastName(),applications.get(i).getJobPosting().getJobId(),
								applications.get(i).getJobPosting().getJobTitle(),applications.get(i).getJobPosting().getJobPostedByCompany().getCompanyName());
					}
					//Sending Congratulatory mail
					else{
						CongratulatoryEmail.sendCongratesMail(application.getApplicant().getEmail(), application.getApplicant().getFirstName(),
								application.getApplicant().getLastName(),application.getJobPosting().getJobId(),
								application.getJobPosting().getJobTitle(),application.getJobPosting().getJobPostedByCompany().getCompanyName());
					}
				}
				
				
				
			}
			
			else continue;
		}
	}
	public boolean companyCancelApplication(String applicationId) {
		// TODO Auto-generated method stub
		JobApplication application= jobApplicationDAO.findOne(applicationId);
		String status=application.getStatus();
		if(status.equalsIgnoreCase("Cancelled") || status.equalsIgnoreCase("OfferAccepted") || status.equalsIgnoreCase("OfferRejected") || status.equalsIgnoreCase("Cancelled")){
			return false;
		}
		else{
			application.setStatus("Cancelled");
			jobApplicationDAO.save(application);
			return true;
		}
	}
	public JobApplication getApplication(String applicationId){
		return jobApplicationDAO.findOne(applicationId);
	}

	public List<JobApplication> updateOffer(JobSeeker applicant, String jobId) {
		
		List<JobApplication> userApplications = findApplications(applicant);
		
		for(JobApplication application : userApplications){
			System.out.println("inside loop");
			System.out.println("inside loop "+application.getId());

			if(application.getJobPosting().getJobId().equals(jobId)){
				// this is the job whose offer has been extended
				System.out.println("inside updating##########");
				application.setStatus("Offered");
				jobSeekerDAO.save(applicant);
				jobApplicationDAO.save(application);
				break;
			}
			
		}
		
		System.out.println("application size "+userApplications.size());
		return userApplications;
	}
}