package edu.cmpe275.termproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
