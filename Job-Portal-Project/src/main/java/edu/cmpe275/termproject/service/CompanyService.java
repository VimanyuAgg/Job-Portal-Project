package edu.cmpe275.termproject.service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edud.cmpe275.termproject.websecurity.SecurityConfig;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private JobPostingDAO jobPostingDAO;	
	
	public Company registerCompany(Company company) throws UnsupportedEncodingException, GeneralSecurityException {
		// TODO Auto-generated method stub
		Company existingCompany=companyDao.findByEmail(company.getEmail());
		if(existingCompany==null){
			company.setPassword(SecurityConfig.encrypt(company.getPassword()));
			
			companyDao.save(company);
			return company;
		}
		else
			return null;
	}
	
	
	public void updateCompany(Company company){
		companyDao.save(company);
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
	//Sets the email verification status to true
	public boolean saveCompanytoDAO(String email){
		Company company = companyDao.findByEmail(email);
		if (company != null){
			company.setVerified();
			companyDao.save(company);
			return true;
		}
		
		return false;
		
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

	public List<JobPosting> getAllPositions(long companyId, String status) {
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

	public long authenticateCompany(String email, String password) throws GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
		Company company=companyDao.findByEmail(email);
		if(company!=null){
			if(password.equals(SecurityConfig.decrypt(company.getPassword())))
				return company.getCompanyId();
		}
		return -100;
	}
	
	public List<Company> getCompanyByName(String position){
		System.out.println("inside getCompanyByName()");
		
		List<String> input = Arrays.asList(position.split(","));
				
		List<Company> companies = companyDao.findCompanyByName(input);
		
		for(Company company : companies){
			System.out.println("inside loop");
			System.out.println("getCompanyName "+company.getCompanyName());
			System.out.println("getAddress "+company.getAddress());
			System.out.println("getCompanyId "+company.getCompanyId());
			System.out.println("getDescription "+company.getDescription());
			System.out.println("getEmail "+company.getEmail());
			System.out.println("getLogoUrl "+company.getLogoUrl());
			System.out.println("getPassword "+company.getPassword());
			System.out.println("getWebsite "+company.getWebsite());
			System.out.println("getCompanyId "+company.getCompanyId());
		}
		
		return companies;
	}

	public String getCompanyByEmail(String email){
		
		Company company = companyDao.findByEmail(email);
		
		if(company == null) return null;
		return "";
	}
	
	public Company getCompany(String email){
		Company company = companyDao.findByEmail(email);
		return company;
	}

	public List<JobPosting> getAllPositions(long companyId) {
		Company company = companyDao.findByCompanyId(companyId);
		if(company == null) return null;
		System.out.println("Line 176 company Id:"+company);
		
		return company.getJobPostingList();
	}


	public void setAuthCode(String authenticationCode_String, String email) {
		// TODO Auto-generated method stub
		Company company  = companyDao.findByEmail(email);
		company.setAuthenticationCode(authenticationCode_String);
		companyDao.save(company);
		System.out.println("Authentication code successfully saved to DAO");
	}
	public String editJob() {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateJob(JobPosting job) {
		
		job.setJobStatus("Cancelled");
		jobPostingDAO.save(job);
		
		return "ok";

	}


}