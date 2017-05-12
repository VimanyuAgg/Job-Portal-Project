package edu.cmpe275.termproject.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.cmpe275.termproject.model.Company;

public interface CompanyDAO extends CrudRepository<Company, Long>{
	Company findByEmail(String email);

}
