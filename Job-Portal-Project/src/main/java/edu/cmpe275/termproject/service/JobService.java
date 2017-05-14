package edu.cmpe275.termproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
@Service
public class JobService {
	@Autowired
	JobPostingDAO jobPostingDao;
	public JobPosting addJob(JobPosting job){
		jobPostingDao.save(job);
		return job;
	}	
	public JobPosting getJob(String jobId){
		return jobPostingDao.findByJobId(jobId);
	}
	public void removeJob(JobPosting job){
		jobPostingDao.delete(job);
	}
	
	public List<JobPosting> getTop10NewJobListings(){
		List<JobPosting> jobList = new ArrayList<JobPosting>();
		PageRequest pr = new PageRequest(10,10);
		 jobList = jobPostingDao.findTop10jobs();
		
		return jobList;
	}
	
	public List<JobPosting> getPositions(String jobId, 
			String title, String location, String salary,
			String status, String postedOn) {
		
		System.out.println("inside getPositions()");
		
		String tempJobIdArray[] = null;
		List<String> jobIdList = new ArrayList();
		List<String> jobTitleList = null;
		List<String> jobLocationList = null;
		List<String> jobSalaryList = null;
		List<String> jobStatusList = null;
		List<String> jobPostedOnList = null;
		int tempTitle = 1, tempJobId = 1, 
				tempJobLocation = 1, tempJobSalary = 1,
				tempJobStatus = 1, tempPostedOn = 1;
		
		if(jobId != null){
			tempJobId = 0;
			jobIdList = Arrays.asList(jobId.split(","));
		}
				
		if(title != null){
			tempTitle = 0;
			jobTitleList = Arrays.asList(title.split(","));
		}
		
		if(location != null){
			tempJobLocation = 0;
			jobLocationList = Arrays.asList(location.split(","));
		}
		
		if(salary != null){
			tempJobSalary = 0;
			jobSalaryList = Arrays.asList(salary.split(","));
		}
		
		if(status != null){
			tempJobStatus = 0;
			jobStatusList = Arrays.asList(status.split(","));
		}
		
		if(postedOn != null){
			tempPostedOn = 0;
			jobPostedOnList = Arrays.asList(postedOn.split(","));
		}
		
		
		System.out.println("tempJobId "+tempJobId);
		System.out.println("tempTitle "+tempTitle);
		System.out.println("tempJobLocation "+tempJobLocation);
		System.out.println("tempJobSalary "+tempJobSalary);
		System.out.println("tempJobStatus "+tempJobStatus);
		System.out.println("tempPostedOn "+tempPostedOn);

		List<JobPosting> positions = jobPostingDao.findJobs(jobIdList, jobTitleList, 
				jobLocationList, jobSalaryList, jobStatusList, jobPostedOnList, tempJobId, 
				tempTitle, tempJobLocation, tempJobSalary, tempJobStatus, tempPostedOn);
		
		//System.out.println("titles "+jobIdList);
		
		
		for(JobPosting position : positions){
			System.out.println("inside loop");
			System.out.println("getJobId  "+position.getJobId());
			System.out.println("getJobDescription "+position.getJobDescription());
			System.out.println("getJobTitle "+position.getJobTitle());
			System.out.println("getJobResponsibilities "+position.getJobResponsibilities());
			System.out.println("getJobLocation "+position.getJobLocation());
			System.out.println("getJobSalary "+position.getJobSalary());
			System.out.println("getJobStatus "+position.getJobStatus());
			System.out.println("getPostedOn "+position.getPostedOn());
			System.out.println("getEligibility "+position.getEligibility());
		}
				
		return positions;
	}	
}
