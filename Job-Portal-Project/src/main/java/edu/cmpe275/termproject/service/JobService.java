package edu.cmpe275.termproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.model.JobPosting;
@Service
public class JobService {
	JobPostingDAO jobPostingDAO;
	public ResponseEntity<?> addJob(JobPosting job){
		jobPostingDAO.save(job);
		return null;
	}
	
}
