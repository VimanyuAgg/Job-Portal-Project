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
	
	@Query("SELECT J FROM JobPosting J WHERE (J.jobDescription LIKE %:desc%)")
	public List<JobPosting> findByJobDescription(@Param("desc") String desc);
	
	@Query("SELECT J FROM JobPosting J WHERE (J.jobTitle LIKE %:jobTitle%)")
	public List<JobPosting> findByJobTitle(@Param("jobTitle") String jobTitle);
	
	@Query("SELECT J FROM JobPosting J WHERE (J.jobResponsibilities LIKE %:jobResp%)")
	public List<JobPosting> findByJobResponsibilities (@Param("jobResp") String jobResp);
	
	@Query("SELECT J FROM JobPosting J WHERE (J.jobLocation LIKE %:location%)")
	public List<JobPosting> findByJobLocation (@Param("location") String location);
	
	@Query("SELECT J FROM JobPosting J WHERE (J.jobSalary LIKE %:salary%)")
	public List<JobPosting> findByJobSalary (@Param("salary") String salary);
	
	@Query("SELECT J FROM JobPosting J WHERE (J.jobStatus LIKE %:status%)")
	public List<JobPosting> findByJobStatus (@Param("status") String status);
	

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
	
	@Query("SELECT J FROM JobPosting J where J.sal BETWEEN :min AND :max")
	public ArrayList<JobPosting> findBySalaryRange(@Param("min") int min, 
												   @Param("max") int max);

	
}

