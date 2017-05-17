package edu.cmpe275.termproject.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobApplication;
import edu.cmpe275.termproject.model.JobSeeker;

public interface JobApplicationDAO extends CrudRepository<JobApplication, String>{

	List<JobApplication> findByApplicant(JobSeeker applicant);
	
}
