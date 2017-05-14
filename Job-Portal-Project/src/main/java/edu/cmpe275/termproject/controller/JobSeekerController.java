//@ModelAttribute("workExperience") String workExperience,
//										 @ModelAttribute("education") String education,
//										 @ModelAttribute("skills") String skills,
//										 @ModelAttribute("password") String password,
//										 @ModelAttribute("authCode") String authCode,
//										 @ModelAttribute("email") String email,

package edu.cmpe275.termproject.controller;

import java.util.ArrayList;
import java.util.List;
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

import edu.cmpe275.termproject.emailService.PasswordSendingEmail;
import edu.cmpe275.termproject.emailService.RegistrationEmail;
import edu.cmpe275.termproject.emailService.WelcomeEmail;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.model.JobSeeker;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.JobSeekerService;
import edu.cmpe275.termproject.service.JobService;
import edu.cmpe275.termproject.service.UserService;

@Controller
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private JobService jobService;
	
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
				workExperience, education, skills, username, email, password);
		
		
		//If existing job seeker and not verified - then delete
		JobSeeker existingjobSeeker=jobSeekerService.getJobSeeker(username);
		if (existingjobSeeker != null && !existingjobSeeker.isVerified()){
			 jobSeekerService.remove(existingjobSeeker);
		 }
		
		jobSeekerService.addJobSeeker(jobSeeker);
		
        String authenticationCode_String = RegistrationEmail.generateAuthCode();
        
		RegistrationEmail.registrationEmailTrigger(email, authenticationCode_String);
		System.out.println("Jobseeker "+firstName+ " saved to DB");
		jobSeekerService.setAuthCode(authenticationCode_String, username);
		redirectAttribute.addFlashAttribute("username",username);
		return "redirect:/jobseeker/authentication";
		
	}
	
	//NEED TO HANDLE CASE OF DIRECT URL HIT
	//AUTHENTICATION - GET
	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.GET)
	private String codeAuthenticationGET(@ModelAttribute ("username") String username){
		
		return "code-authentication";
	}
	
	//AUTHENTICATION - POST
	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.POST)
	private String codeAuthenticationPOST(HttpServletRequest request, RedirectAttributes redirectAttribute){
		
		String username = request.getParameter("username");
		String passCode = request.getParameter("codeVerification");
		boolean userExists = jobSeekerService.find(username);
		System.out.println("User Name: "+username);
		String authCode = jobSeekerService.getJobSeeker(username).getAuthenticationCode();
		System.out.println("Email code: "+authCode);
		System.out.println("User code: " +passCode);
		if(userExists&& passCode.equals(authCode)){
			redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
			
			WelcomeEmail.welcomeEmailTrigger(jobSeekerService.getJobSeeker(username).getEmail(), 
											 jobSeekerService.getJobSeeker(username).getFirstName(),
											 jobSeekerService.getJobSeeker(username).getLastName(),
											 username);
			PasswordSendingEmail.deliverPasswordEmail(jobSeekerService.getJobSeeker(username).getEmail(), 
					 jobSeekerService.getJobSeeker(username).getFirstName(),
					 jobSeekerService.getJobSeeker(username).getLastName(),
					 jobSeekerService.getJobSeeker(username).getPassword());
			//System.out.println("Jobseeker "+firstName+ " saved to DB");
			return "redirect:/jobseeker/login";
		}
		else {
			return "jobseeker/authentication";
		}
		
		
	}
	

	
	//LOGIN - GET
	@RequestMapping(value="/jobseeker/login", method=RequestMethod.GET)
	public String jobSeekerLogin(@ModelAttribute ("username") String username)
	{
		
		return "jobseeker-login";	
		
	}
	
	//LOGIN - POST
	@RequestMapping(value="/jobseeker/login", method=RequestMethod.POST)
	public String jobSeekerLoginPost(HttpServletRequest request,
									 RedirectAttributes redirectAttribute){
		//, RedirectAttributes redirectAttribute
		String username = request.getParameter("username"),
		       password = request.getParameter("password");
		
		System.out.println("username: "+username);
		System.out.println("password:"+password);
		
		String usersess = jobSeekerService.authenticateJobSeeker(username, password);
		System.out.println("Printing usersess: "+usersess);
		if(!usersess.isEmpty()){
			httpSession.setAttribute("username",username);
			redirectAttribute.addFlashAttribute("topJobs",jobService.getTop10NewJobListings());
			redirectAttribute.addFlashAttribute("selfIntroduction",jobSeekerService.getJobSeeker(username).getSelfIntroduction());
			redirectAttribute.addFlashAttribute("picture",jobSeekerService.getJobSeeker(username).getPicture());
			redirectAttribute.addFlashAttribute("firstName",jobSeekerService.getJobSeeker(username).getFirstName());
			redirectAttribute.addFlashAttribute("lastName",jobSeekerService.getJobSeeker(username).getLastName());
			//httpSession.setAttribute("userID", userN);
			//redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
			return "redirect:/jobseeker/dashboard";
		}
		else
		return "redirect:/jobseeker/login"; 
		
		
	}
	

	//DASHBOARD - GET
	@RequestMapping(value="/jobseeker/dashboard",method=RequestMethod.GET)
	public String jobSeekerDashBoard(@ModelAttribute("selfIntroduction") String selfIntroduction,
									 @ModelAttribute("firstName") String firstName,
									 @ModelAttribute("lastName") String lastName,
									 @ModelAttribute("picture") String picture,
									 @ModelAttribute("topJobs") ArrayList<JobPosting> topJobs
									 ){
		System.out.println("Inside GET Jobseeker");
		return "jobseeker-dashboard";
	}
	
}

//package edu.cmpe275.termproject.controller;
//
//import java.util.UUID;
//
//import javax.mail.Message;
//import javax.mail.internet.MimeMessage;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import edu.cmpe275.termproject.emailService.RegistrationEmail;
//import edu.cmpe275.termproject.emailService.WelcomeEmail;
//import edu.cmpe275.termproject.model.Company;
//import edu.cmpe275.termproject.model.JobSeeker;
//import edu.cmpe275.termproject.service.CompanyService;
//import edu.cmpe275.termproject.service.JobSeekerService;
//import edu.cmpe275.termproject.service.UserService;
//
//@Controller
//public class JobSeekerController {
//	
//	@Autowired
//	private JobSeekerService jobSeekerService;
//	
//	@Autowired
//	HttpSession httpSession;
//	
//	//REGISTRATION - GET
//	@RequestMapping(value="/jobseeker/register", method=RequestMethod.GET)
//	public String getJobSeekerView(){
//		return "jobseeker-registration";
//		
//	}
//	
//	//REGISTRATION - POST
//	@RequestMapping(value="/jobseeker/register", method=RequestMethod.POST)
//	public String createJobSeeker(HttpServletRequest request, RedirectAttributes redirectAttribute){
//		
//		String firstName=request.getParameter("firstName"), 
//				lastName=request.getParameter("lastName"), 
//				picture=request.getParameter("picture"),
//				selfIntroduction=request.getParameter("selfIntroduction"), 
//				workExperience=request.getParameter("workExperience"), 
//				education=request.getParameter("education"), 
//			    skills=request.getParameter("skills"), 
//			    password=request.getParameter("password"),
//			    username=request.getParameter("username"),
//			    email = request.getParameter("email");
//		
//		JobSeeker jobSeeker=new JobSeeker(firstName,lastName, picture, selfIntroduction,
//				workExperience, education, skills, username, email, password, null);
//		
//		jobSeekerService.addJobSeeker(jobSeeker);
//		
//        String authenticationCode_String = RegistrationEmail.generateAuthCode();
//        
//		RegistrationEmail.registrationEmailTrigger(email, authenticationCode_String);
//		System.out.println("Jobseeker "+firstName+ " saved to DB");
//		jobSeekerService.setAuthCode(authenticationCode_String, username);
//		redirectAttribute.addFlashAttribute("username",username);
//		boolean isRedirectedFromRegistration = true;
//		redirectAttribute.addFlashAttribute("redirected",isRedirectedFromRegistration);
//		redirectAttribute.addFlashAttribute("firstName",firstName);
//		redirectAttribute.addFlashAttribute("lastName",lastName);
//		redirectAttribute.addFlashAttribute("picture",picture);
//		redirectAttribute.addFlashAttribute("selfIntroduction",selfIntroduction);
//		redirectAttribute.addFlashAttribute("workExperience",workExperience);
//		redirectAttribute.addFlashAttribute("education",education);
//		redirectAttribute.addFlashAttribute("skills",skills);
//		redirectAttribute.addFlashAttribute("password",password);
//		redirectAttribute.addFlashAttribute("username",username);
//		redirectAttribute.addFlashAttribute("email",email);
//		
//		return "redirect:/jobseeker/authentication";
//		
//	}
//	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.GET)
//	private String codeAuthenticationGET(@ModelAttribute ("username") String username,
//										 @ModelAttribute("redirected") boolean isRedirected,
//										 @ModelAttribute("firstName") String firstName,
//										 @ModelAttribute("picture") String picture,
//										 @ModelAttribute("selfIntroduction")String selfIntroducio){
//		try{
//			if(isRedirected){
//				JobSeeker jobSeeker=new JobSeeker(firstName,lastName, picture, selfIntroduction,
//						workExperience, education, skills, username, email, password, null);
//				
//				jobSeekerService.addJobSeeker(jobSeeker);
//				
//				return "code-authentication";
//			}
//			else{
//				return "session-error";
//			}
//			
//		}catch (Exception e){
//			return "session-error";
//		}
//		
//	}
//	
//	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.POST)
//	private String codeAuthenticationPOST(HttpServletRequest request, RedirectAttributes redirectAttribute){
//		
//		String username = request.getParameter("username");
//		String passCode = request.getParameter("codeVerification");
//		boolean userExists = jobSeekerService.find(username);
//		System.out.println("User Name: "+username);
//		String authCode = jobSeekerService.getJobSeeker(username).getAuthenticationCode();
//		System.out.println("Email code: "+authCode);
//		System.out.println("User code: " +passCode);
//		if(userExists&& passCode.equals(authCode)){
//			redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
//			
//			WelcomeEmail.welcomeEmailTrigger(jobSeekerService.getJobSeeker(username).getEmail(), username);
//			//System.out.println("Jobseeker "+firstName+ " saved to DB");
//			return "redirect:/jobseeker/login";
//		}
//		else {
//			return "jobseeker/authentication";
//		}
//		
//		
//	}
//	
//	
//	//Removing jobseeker/created path
//	
////	@RequestMapping(value="/jobseeker/created", method=RequestMethod.GET)
////	public String jobSeekerCreated(@ModelAttribute ("username") String username){
////		return "jobseeker-created";
////		
////	}
//	
//	@RequestMapping(value="/jobseeker/dashboard",method=RequestMethod.GET)
//	public String jobSeekerDashBoard(){
//		return "jobseeker-dashboard";
//	}
//	
//	@RequestMapping(value="/jobseeker/login", method=RequestMethod.GET)
//	public String jobSeekerLogin(@ModelAttribute ("username") String username)
//	{
//		
//		return "jobseeker-login";
//	
//		
//	}
//	
//	@RequestMapping(value="/jobseeker/login", method=RequestMethod.POST)
//	public String jobSeekerLoginPost(HttpServletRequest request){
//		//, RedirectAttributes redirectAttribute
//		String username = request.getParameter("username"),
//		       password = request.getParameter("password");
//		
//		String usersess = jobSeekerService.authenticateJobSeeker(username, password);
//		System.out.println(usersess);
//		if(!usersess.isEmpty()){
//			httpSession.setAttribute("username",username);
//			//httpSession.setAttribute("userID", userN);
//			//redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
//			return "redirect:/jobseeker/dashboard";
//		}
//		else
//		return "redirect:/usersession-error"; 
//		
//		
//	}
//	
//}
