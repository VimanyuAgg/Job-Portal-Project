package edu.cmpe275.termproject.controller;

import java.util.UUID;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.cmpe275.termproject.emailService.RegistrationEmail;
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
	public String createJobSeeker(HttpServletRequest request, RedirectAttributes redirectAttribute){
		
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
				workExperience, education, skills, username, email, password, null);
		
		jobSeekerService.addJobSeeker(jobSeeker);
		UUID authenticationCode = UUID.randomUUID();
        String authenticationCode_String = authenticationCode.toString();
        
		RegistrationEmail.registrationEmailTrigger(email, authenticationCode_String);
		System.out.println("Jobseeker "+firstName+ " saved to DB");
		redirectAttribute.addFlashAttribute("authenticationCode",authenticationCode_String);
		redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
		return "redirect:/jobseeker/authentication";
		
	}
	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.GET)
	private String codeAuthenticationGET(@ModelAttribute ("username") String username){
		
		return "code-authentication";
	}
	
	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.POST)
	private String codeAuthenticationGET(HttpServletRequest request,RedirectAttributes redirectAttribute,
			@ModelAttribute ("username") String username,@ModelAttribute ("authenticationCode") String authCode){
		
		//String username = request.getParameter("username");
		String passCode = request.getParameter("codeVerification");
		boolean userExists = jobSeekerService.find(username);
		System.out.println("User Name: "+username);
		System.out.println("Email code: "+authCode);
		System.out.println("User code: " +passCode);
		if(userExists&& passCode.equals(authCode)){
			redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
			return "redirect:/jobseeker/login";
		}
		else {
			return "jobseeker/authentication";
		}
		
		
	}
	
	
	//Removing jobseeker/created path
	
//	@RequestMapping(value="/jobseeker/created", method=RequestMethod.GET)
//	public String jobSeekerCreated(@ModelAttribute ("username") String username){
//		return "jobseeker-created";
//		
//	}
	
	@RequestMapping(value="/jobseeker/dashboard",method=RequestMethod.GET)
	public String jobSeekerDashBoard(){
		return "jobseeker-dashboard";
	}
	
	@RequestMapping(value="/jobseeker/login", method=RequestMethod.GET)
	public String jobSeekerLogin(@ModelAttribute ("username") String username)
	{
		
		return "jobseeker-login";
	
		
	}
	
	@RequestMapping(value="/jobseeker/login", method=RequestMethod.POST)
	public String jobSeekerLoginPost(HttpServletRequest request){
		//, RedirectAttributes redirectAttribute
		String username = request.getParameter("username"),
		       password = request.getParameter("password");
		
		String usersess = jobSeekerService.authenticateJobSeeker(username, password);
		System.out.println(usersess);
		if(!usersess.isEmpty()){
			httpSession.setAttribute("username",username);
			//httpSession.setAttribute("userID", userN);
			//redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
			return "redirect:/jobseeker/dashboard";
		}
		else
		return "redirect:/usersession-error"; 
		
		
	}
	
}
