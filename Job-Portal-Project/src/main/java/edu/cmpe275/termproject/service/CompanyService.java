package edu.cmpe275.termproject.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDao;
	public Company registerCompany(Company company) {
		// TODO Auto-generated method stub
		Company existingCompany=companyDao.findByEmail(company.getEmail());
		if(existingCompany==null){
			companyDao.save(company);
			return company;
		}
		else
			return null;
	}
	
	//public ResponseEntity<?> addJobPosting(String)
	public Company getCompany(long companyId){
		Company company= companyDao.findOne(companyId);
		if(company!=null){
			return company;
		}
		else{
			System.out.println("I am in null");
			return null;
		}
	}

	/*private JSONObject generateErrorMessage(String message) {
		// TODO Auto-generated method stub
		JSONObject json= new JSONObject();
		try {
			json.put("errorMesshage", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return json;
	}*/

	public List<JobPosting> getAllPositions(long companyId,@RequestParam(value = "status", required=false) String status) {
		// TODO Auto-generated method stub
		Company company = companyDao.findByCompanyId(companyId);
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

	public long authenticateCompany(String email, String password) {
		// TODO Auto-generated method stub
		Company company=companyDao.findByEmail(email);
		if(company!=null){
			if(company.getPassword().equals(password))
				return company.getCompanyId();
		}
		return -100;
	}
	
	public List<Company> getCompanyByName(String position){
		System.out.println("inside getCompanyByName()");
		
		//String input2[] = position.split(",");
		List<String> input = Arrays.asList(position.split(","));
				
		List<Company> companies = companyDao.findCompanyByName(input);
		
		for(Company company : companies){
			System.out.println("inside loop");
			System.out.println("company name "+company.getCompanyName());
			System.out.println("company name "+company.getAddress());
			System.out.println("company name "+company.getCompanyId());
			System.out.println("company name "+company.getDescription());
			System.out.println("company name "+company.getEmail());
			System.out.println("company name "+company.getLogoUrl());
			System.out.println("company name "+company.getPassword());
			System.out.println("company name "+company.getWebsite());
		}
		
		return companies;
	}
}