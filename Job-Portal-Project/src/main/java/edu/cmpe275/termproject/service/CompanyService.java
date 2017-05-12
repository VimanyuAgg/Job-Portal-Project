
package edu.cmpe275.termproject.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;

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
	public Company getCompany(long companyId){
		Company company= companyDAO.findOne(companyId);
		if(company!=null){
			//return new ResponseEntity<String>(new JSONObject(company).toString(),HttpStatus.OK);
			return company;
		}
		else{
			System.out.println("I am in null");
			//return new ResponseEntity<String>(generateErrorMessage("Company Does not exist").toString(),HttpStatus.NOT_FOUND);
			return null;
		}
	}

	private JSONObject generateErrorMessage(String message) {
		// TODO Auto-generated method stub
		JSONObject json= new JSONObject();
		try {
			json.put("errorMesshage", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return json;
	}

	public List<JobPosting> getAllPositions(long companyId, String status) {
		// TODO Auto-generated method stub
		//Company company = companyDAO.findByCompanyId();
		Company company=null;
		if(company==null)
			return null;
		List<JobPosting> jobs=company.getJobPostingList();
		if(status==null)
			return jobs;
		List<JobPosting> resultList=new ArrayList<JobPosting>();
		for(JobPosting job:jobs){
			if(job.getJobStatus().equals(status))
				resultList.add(job);
		}
		return resultList;
	}

	public boolean authenticateCompany(String email, String password) {
		// TODO Auto-generated method stub
		Company company=companyDAO.findByEmail(email);
		if(company!=null){
			if(company.getPassword().equals(password))
				return true;
		}
		return false;
	}
}
//	public String companyToJsonString(Company company){
		
//	}

