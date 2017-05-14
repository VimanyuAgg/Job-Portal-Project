package edu.cmpe275.termproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public JobPosting getJob(long jobId){
		return jobPostingDao.findByJobId(jobId);
	}
	public void removeJob(JobPosting job){
		jobPostingDao.delete(job);
	}
	
	public List<JobPosting> getPositions(String jobId) {
		
		System.out.println("inside getPositions()");
		
		String input2[] = jobId.split(",");
		List<Long> input = new ArrayList();
				
		for(int i=0;i<input2.length;i++){
			input.add(Long.parseLong(input2[i]));
		}

		System.out.println("calling query");		
		List<JobPosting> positions = jobPostingDao.findJobs(input);
		
		for(JobPosting position : positions){
			System.out.println("inside loop");
			System.out.println("position name "+position.getJobId());
			System.out.println("position name "+position.getJobDescription());
			System.out.println("position name "+position.getJobTitle());
			System.out.println("position name "+position.getJobResponsibilities());
			System.out.println("position name "+position.getJobLocation());
			System.out.println("position name "+position.getJobSalary());
			System.out.println("position name "+position.getJobStatus());
			System.out.println("position name "+position.getPostedOn());
			System.out.println("position name "+position.getEligibility());
		}
		
		return positions;
	}	
}
