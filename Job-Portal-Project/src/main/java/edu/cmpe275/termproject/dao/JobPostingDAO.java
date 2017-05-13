package edu.cmpe275.termproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;

public interface JobPostingDAO extends CrudRepository<JobPosting,Integer> {

	@Query("SELECT j FROM JobPosting j WHERE LOWER(j.jobId) IN (:jobId)")
    public List<JobPosting> findJobs(@Param("jobId") List<String> jobId);

}
