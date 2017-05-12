package edu.cmpe275.termproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/jobseeker/register", method=RequestMethod.GET)
	public String getJobSeekerView(){
		return "jobseeker-registration";
		
	}
	
	@RequestMapping(value="/jobseeker/register", method=RequestMethod.POST)
	public String createJobSeeker(HttpServletRequest request){
		String firstName=request.getParameter("firstName"), lastName=request.getParameter("lastName"), 
				picture=request.getParameter("picture"),selfIntroduction=request.getParameter("selfIntroduction"), 
				workExperience=request.getParameter("workExperience"), education=request.getParameter("education"), 
			    skills=request.getParameter("skills"), password=request.getParameter("password"),
			    username=request.getParameter("username"),email = request.getParameter("email");
		JobSeeker jobSeeker=new JobSeeker(name, website, logoImageUrl, address, description, email ,password);
		companyService.addCompany(company);
		return "redirect:/companycreated";
		
	}
}
