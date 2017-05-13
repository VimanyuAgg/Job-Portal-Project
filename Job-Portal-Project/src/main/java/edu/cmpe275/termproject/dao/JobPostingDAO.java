package edu.cmpe275.termproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;

<<<<<<< HEAD
public interface JobPostingDAO extends CrudRepository<JobPosting,Integer> {
	public JobPosting findByJobId(long jobId);
=======
public interface JobPostingDAO extends CrudRepository<JobPosting, Long> {

	@Query("SELECT j FROM JobPosting j WHERE j.jobId IN (:jobId)")
    public List<JobPosting> findJobs(@Param("jobId") List<Long> jobId);

>>>>>>> 59897ede07aa3eb740c246a8b6b2668cfbcdf685
}
