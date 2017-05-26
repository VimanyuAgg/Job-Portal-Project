package edu.cmpe275.termproject.dao;

import org.springframework.data.repository.CrudRepository;

import edu.cmpe275.termproject.model.JobSeeker;


public interface JobSeekerDAO extends CrudRepository<JobSeeker,Long>{

	JobSeeker findByUsername(String username);
	JobSeeker findByEmail(String email);
	JobSeeker findByJsid(Long jsid);

}
