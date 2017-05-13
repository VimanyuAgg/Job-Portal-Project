package edu.cmpe275.termproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.dao.JobSeekerDAO;
import edu.cmpe275.termproject.model.JobSeeker;

@Service
public class JobSeekerService {

	@Autowired
	private JobSeekerDAO jobSeekerDAO;
	
	//POST
	public void addJobSeeker(JobSeeker jobSeeker){
		jobSeekerDAO.save(jobSeeker);
		
	}
	
	//GET-ALL
	public void getAllUsers(){
		jobSeekerDAO.findAll();
	}

	public String authenticateJobSeeker(String username, String password) {
		// TODO Auto-generated method stub
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(username);
		if(jobSeeker.getPassword().equals(password))
			return username;
		else
			return "";
	}
}
