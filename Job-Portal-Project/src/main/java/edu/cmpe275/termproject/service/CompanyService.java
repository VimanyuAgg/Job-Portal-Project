package edu.cmpe275.termproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.model.Company;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	public ResponseEntity<?> addCompany(Company company) {
		// TODO Auto-generated method stub
		companyDAO.save(company);
		return null;
	}
	
	//public ResponseEntity<?> addJobPosting(String)
}
