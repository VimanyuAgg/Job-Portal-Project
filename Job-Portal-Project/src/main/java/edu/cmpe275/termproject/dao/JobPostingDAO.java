package edu.cmpe275.termproject.dao;

import org.springframework.data.repository.CrudRepository;

import edu.cmpe275.termproject.model.JobPosting;

public interface JobPostingDAO extends CrudRepository<JobPosting,Integer> {
	public JobPosting findByJobId(long jobId);
}
