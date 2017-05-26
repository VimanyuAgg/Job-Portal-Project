package edu.cmpe275.termproject.service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import edu.cmpe275.termproject.dao.JobSeekerDAO;
import edu.cmpe275.termproject.model.JobSeeker;
import edud.cmpe275.termproject.websecurity.SecurityConfig;

@Service
public class JobSeekerService {

	@Autowired
	private JobSeekerDAO jobSeekerDAO;
//	
//	@Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//	
	//POST
	public void addJobSeeker(JobSeeker jobSeeker) throws UnsupportedEncodingException, GeneralSecurityException{
		jobSeeker.setPassword(SecurityConfig.encrypt(jobSeeker.getPassword()));
		jobSeekerDAO.save(jobSeeker);
		
	}
	public void updateJobSeeker(JobSeeker jobSeeker) throws UnsupportedEncodingException, GeneralSecurityException{
		jobSeekerDAO.save(jobSeeker);
	}
	
	//GET-ALL
	public void getAllUsers(){
		jobSeekerDAO.findAll();
	}

	public String authenticateJobSeeker(String username, String password) throws GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(username);
		System.out.println("Inside authenticateJobSeeker"+jobSeeker);
		if(jobSeeker == null){
			return "";
		}
		System.out.println("firstName: "+jobSeeker.getFirstName());

		System.out.println("DB PSW: "+jobSeeker.getPassword());
		System.out.println("DB DecryptedPassword: "+ SecurityConfig.decrypt(jobSeeker.getPassword()));
		System.out.println("User PSW: "+password);
		System.out.println("Encrypted usr PSW:    "+SecurityConfig.encrypt(password));
		if(password.equals(SecurityConfig.decrypt(jobSeeker.getPassword())))

		{
			System.out.println("Jobseeker exists");
			return username;
		}
		else
			return "";
	}

	public boolean saveJobSeekerToDB(String username) {
		// TODO Auto-generated method stub
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(username);
		if(jobSeeker != null){
			System.out.println("setting verification status to True for user: "+username);
			jobSeeker.setVerified();
			jobSeekerDAO.save(jobSeeker);
			return true;
		}
		
		return false;
	}
	
	public JobSeeker getJobSeeker(String username){
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(username);
		return jobSeeker;
	}

	public void setAuthCode(String authenticationCode_String, String username) {
		// TODO Auto-generated method stub
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(username);
		jobSeeker.setAuthenticationCode(authenticationCode_String);
		jobSeekerDAO.save(jobSeeker);
		System.out.println("Authentication code successfully saved to DAO");
	}

	public void remove(JobSeeker existingjobSeeker) {
		// TODO Auto-generated method stub
		
		jobSeekerDAO.delete(existingjobSeeker);
		System.out.println("Service Layer - JobSeeker deleted from DB");
		
	}

	public String getJobSeekerByEmail(String email) {
		
		JobSeeker jobSeeker = jobSeekerDAO.findByEmail(email);
		
		if(jobSeeker != null){
			return jobSeeker.getUsername();
		}
		return null;
	}

	public JobSeeker findByUsername(String username) {
		
		return jobSeekerDAO.findByUsername(username);
	}

	public JobSeeker findByEmail(String email) {
		
		return jobSeekerDAO.findByEmail(email);
	}

public JobSeeker findByJsid(Long jsid) {
	
	return jobSeekerDAO.findByJsid(jsid);
}


}