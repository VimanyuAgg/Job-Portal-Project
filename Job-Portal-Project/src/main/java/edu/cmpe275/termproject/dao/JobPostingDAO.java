package edu.cmpe275.termproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import edu.cmpe275.termproject.model.JobPosting;

public interface JobPostingDAO extends CrudRepository<JobPosting, String> {
	public JobPosting findByJobId(String jobId);

	@Query("SELECT j FROM JobPosting j WHERE (j.tempSize = :tempJobTitle OR LOWER(j.jobTitle) IN (:title)) "
			+ "AND (j.tempSize = :tempJobLocation OR j.jobLocation IN :jobLocation)"
			+ "AND (j.tempSize = :tempJobSalary OR j.jobSalary IN :jobSalary)"
			+ "AND (j.tempSize = :tempJobStatus OR j.jobStatus IN :jobStatus)"
			+ "AND (j.tempSize = :tempJobPostedOn OR j.postedOn IN :jobPostedOn)"
			+ "AND (j.tempSize = 1)")
    public List<JobPosting> findJobs(
    		@Param("title") List<String> title, 
    		@Param("jobLocation") List<String> jobLocation, 
    		@Param("jobSalary") List<String> jobSalary, 
    		@Param("jobStatus") List<String> jobStatus, 
    		@Param("jobPostedOn") List<String> jobPostedOn, 
    		@Param("tempJobTitle") int tempJobTitle,
			@Param("tempJobLocation") int tempJobLocation,
			@Param("tempJobSalary") int tempJobSalary,
			@Param("tempJobStatus") int tempJobStatus,
			@Param("tempJobPostedOn") int tempJobPostedOn);
	
	@Query("SELECT J FROM JobPosting J ORDER BY J.postedOn DESC")
	public ArrayList<JobPosting> findTop10jobs();
}

