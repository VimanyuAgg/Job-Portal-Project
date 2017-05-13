package edu.cmpe275.termproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobSeeker;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.JobSeekerService;
import edu.cmpe275.termproject.service.UserService;

@Controller
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	HttpSession httpSession;
	
	//REGISTRATION - GET
	@RequestMapping(value="/jobseeker/register", method=RequestMethod.GET)
	public String getJobSeekerView(){
		return "jobseeker-registration";
		
	}
	
	//REGISTRATION - POST
	@RequestMapping(value="/jobseeker/register", method=RequestMethod.POST)
	public String createJobSeeker(HttpServletRequest request){
		String firstName=request.getParameter("firstName"), 
				lastName=request.getParameter("lastName"), 
				picture=request.getParameter("picture"),
				selfIntroduction=request.getParameter("selfIntroduction"), 
				workExperience=request.getParameter("workExperience"), 
				education=request.getParameter("education"), 
			    skills=request.getParameter("skills"), 
			    password=request.getParameter("password"),
			    username=request.getParameter("username"),
			    email = request.getParameter("email");
		
		JobSeeker jobSeeker=new JobSeeker(firstName,lastName, picture, selfIntroduction,
				workExperience, education, skills, username, email, password);
		
		jobSeekerService.addJobSeeker(jobSeeker);
		System.out.println("Jobseeker "+firstName+ " saved to DB");
		return "redirect:/jobseeker/created";
		
	}
	
	@RequestMapping(value="/jobseeker/created", method=RequestMethod.GET)
	public String jobSeekerCreated(){
		return "jobseeker-created";
		
	}
	
	@RequestMapping(value="/jobseeker/dashboard",method=RequestMethod.GET)
	public String jobSeekerDashBoard(){
		return "jobseeker-dashboard";
	}
	
	@RequestMapping(value="/jobseeker/login", method=RequestMethod.GET)
	public String jobSeekerLogin()
	{
		
		return "jobseeker-login";
	
		
	}
	
	@RequestMapping(value="/jobseeker/login", method=RequestMethod.POST)
	public String jobSeekerLoginPost(HttpServletRequest request){
		String username = request.getParameter("username"),
		       password = request.getParameter("password");
		
		String usersess = jobSeekerService.authenticateJobSeeker(username, password);
		System.out.println(usersess);
		if(!usersess.isEmpty()){
			httpSession.setAttribute("username",username);
			//httpSession.setAttribute("userID", userN);
			return "redirect:/jobseeker/dashboard";
		}
		else
		return "redirect:/usersession-error"; 
		
		
	}
	
}
