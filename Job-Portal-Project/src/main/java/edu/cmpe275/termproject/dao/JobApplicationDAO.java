package edu.cmpe275.termproject.dao;

import org.springframework.data.repository.CrudRepository;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobApplication;

public interface JobApplicationDAO extends CrudRepository<JobApplication, Long>{

}
