package edu.cmpe275.termproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.JobPostingDAO;
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
}
