//@ModelAttribute("workExperience") String workExperience,
//										 @ModelAttribute("education") String education,
//										 @ModelAttribute("skills") String skills,
//										 @ModelAttribute("password") String password,
//										 @ModelAttribute("authCode") String authCode,
//										 @ModelAttribute("email") String email,

package edu.cmpe275.termproject.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
//import edu.cmpe275.termproject.service.UserService;
import edud.cmpe275.termproject.websecurity.SecurityConfig;

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
	public String createJobSeeker(HttpServletRequest request, RedirectAttributes redirectAttribute) throws UnsupportedEncodingException, GeneralSecurityException{
		
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
		System.out.println("Found Existing jobseeker"+ existingjobSeeker);
		if (existingjobSeeker != null && !existingjobSeeker.isVerified()){
			System.out.println("deleting the existing user !"); 
			jobSeekerService.remove(existingjobSeeker);
			
		 }
		
		jobSeekerService.addJobSeeker(jobSeeker);
		
        String authenticationCode_String = RegistrationEmail.generateAuthCode();
        
		RegistrationEmail.registrationEmailTrigger(email, authenticationCode_String);
		System.out.println("Jobseeker "+firstName+ " saved to DB");
		jobSeekerService.setAuthCode(authenticationCode_String, username);
		redirectAttribute.addFlashAttribute("username",username);
		redirectAttribute.addFlashAttribute("isRedirected","true");
		return "redirect:/jobseeker/authentication";
		
	}
	
	
	//AUTHENTICATION - GET
	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.GET)
	private String codeAuthenticationGET(@ModelAttribute ("username") String username,
										 @ModelAttribute("isRedirected") String isRedirected){
		
		if (("true").equals(isRedirected)){
			return "code-authentication";
		}
		else{
			return "redirect:/jobseeker/login";
		}
	}
	
	//AUTHENTICATION - POST
	@RequestMapping(value="/jobseeker/authentication",method=RequestMethod.POST)
	private String codeAuthenticationPOST(HttpServletRequest request, RedirectAttributes redirectAttribute) throws GeneralSecurityException, IOException{
		
		String username = request.getParameter("username");
		String passCode = request.getParameter("codeVerification");
		JobSeeker userExists = jobSeekerService.getJobSeeker(username);
		System.out.println("User Name: "+username);
		String authCode = jobSeekerService.getJobSeeker(username).getAuthenticationCode();
		System.out.println("Email code: "+authCode);
		System.out.println("User code: " +passCode);
		if(userExists != null && passCode.equals(authCode)){
			redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
			
			WelcomeEmail.welcomeEmailTrigger(jobSeekerService.getJobSeeker(username).getEmail(), 
											 jobSeekerService.getJobSeeker(username).getFirstName(),
											 jobSeekerService.getJobSeeker(username).getLastName(),
											 username);
			PasswordSendingEmail.deliverPasswordEmail(jobSeekerService.getJobSeeker(username).getEmail(), 
					 jobSeekerService.getJobSeeker(username).getFirstName(),
					 jobSeekerService.getJobSeeker(username).getLastName(),
					 SecurityConfig.decrypt(jobSeekerService.getJobSeeker(username).getPassword()));
			jobSeekerService.saveJobSeekerToDB(username);
			System.out.println("Jobseeker "+username+ " saved to DB");
			
			return "redirect:/jobseeker/login";
		}
		else {
			System.out.println("Authentication code did not match");
			redirectAttribute.addFlashAttribute("username", username);
			redirectAttribute.addFlashAttribute("isRedirected","true");
			redirectAttribute.addFlashAttribute("isBadOTP","true");
			redirectAttribute.addFlashAttribute("badOTP","Sorry, the OTP you provided is incorrect");
			
			return "redirect:/jobseeker/authentication/error";
		}
		
		
	}
	
	//AUTHENTICATION GET - ERROR
	@RequestMapping(value="/jobseeker/authentication/error",method=RequestMethod.GET)
	private String codeAuthenticationGETError(@ModelAttribute ("username") String username,
										 @ModelAttribute("isRedirected") String isRedirected,
										 @ModelAttribute("badOTP") String badOTP,
										 @ModelAttribute("isBadOTP") String isBadOTP){
		
		System.out.println("Inside Authentication GET-ERROR");
		if (("true").equals(isRedirected)){
			return "code-authentication";
		}
		else{
			return "redirect:/jobseeker/login";
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
									 RedirectAttributes redirectAttribute) throws GeneralSecurityException, IOException{
		//, RedirectAttributes redirectAttribute
		String username = request.getParameter("username"),
		       password = request.getParameter("password");
		
		System.out.println("username: "+username);
		System.out.println("password:"+password);
		
		String usersess = jobSeekerService.authenticateJobSeeker(username, password);
		System.out.println("Printing usersess: "+usersess);
		JobSeeker jobSeeker = jobSeekerService.getJobSeeker(username);
		if(!usersess.isEmpty()){
			if(!jobSeeker.isVerified()){
				redirectAttribute.addFlashAttribute("notFoundText", "Sorry you are not verified ! Please re-register and authenticate");
				String isNotFound = "true";
				redirectAttribute.addFlashAttribute("isNotFound", isNotFound);
				return "redirect:/jobseeker/login/error";
			}
			httpSession.setAttribute("username",username);
			httpSession.setAttribute("email",jobSeeker.getEmail());
			httpSession.setAttribute("firstName", jobSeeker.getFirstName());
			httpSession.setAttribute("lastName", jobSeeker.getLastName());
			httpSession.setAttribute("education", jobSeeker.getEducation());
			httpSession.setAttribute("skills", jobSeeker.getSkills());
			httpSession.setAttribute("workExperience", jobSeeker.getWorkExperience());
			httpSession.setAttribute("picture", jobSeeker.getPicture());
			redirectAttribute.addFlashAttribute("topJobs",jobService.getTop10NewJobListings());
			redirectAttribute.addFlashAttribute("selfIntroduction",jobSeekerService.getJobSeeker(username).getSelfIntroduction());
			redirectAttribute.addFlashAttribute("picture",jobSeekerService.getJobSeeker(username).getPicture());
			redirectAttribute.addFlashAttribute("firstName",jobSeekerService.getJobSeeker(username).getFirstName());
			redirectAttribute.addFlashAttribute("lastName",jobSeekerService.getJobSeeker(username).getLastName());
			//httpSession.setAttribute("userID", userN);
			//redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+username);
			System.out.println("redirecting to dashboard");
			return "redirect:/jobseeker/"+username+"/dashboard";
		}
		else{
			redirectAttribute.addFlashAttribute("notFoundText","Sorry, username/password is invalid");
			String isNotFound = "true";
		    redirectAttribute.addFlashAttribute("isNotFound",isNotFound);
		return "redirect:/jobseeker/login/error"; 
		}
		
	}
	
	//LOGIN - GET ERROR
		@RequestMapping(value="/jobseeker/login/error", method=RequestMethod.GET)
		public String jobSeekerLogin(@ModelAttribute ("username") String username,
									 @ModelAttribute ("isNotFound") String isNotFound,
									 @ModelAttribute ("notFoundText") String notFoundText)
									
		{
			
			
			return "jobseeker-login";	
			
		}
	

	//DASHBOARD - GET
	@RequestMapping(value="/jobseeker/{username}/dashboard",method=RequestMethod.GET)
	public String jobSeekerDashBoard(@ModelAttribute("selfIntroduction") String selfIntroduction,
									 @ModelAttribute("firstName") String firstName,
									 @ModelAttribute("lastName") String lastName,
									 @ModelAttribute("picture") String picture,
									 @ModelAttribute("topJobs") ArrayList<JobPosting> topJobs,
									 @PathVariable String username ){
		System.out.println("Inside GET Jobseeker dashboard");
		System.out.println("username: "+username);
		System.out.println("httpsession getAttribute: "+httpSession.getAttribute(username));
		System.out.println("httpSession getAttString: "+httpSession.getAttribute("username"));

		if(httpSession.getAttribute("username") == null || !httpSession.getAttribute("username").equals(username)){
			return "redirect:/jobseeker/login";
		}
		
		
		return "jobseeker-dashboard";
	}
	
	//LOGOUT - GET
	@RequestMapping(value="/jobseeker/logout", method=RequestMethod.GET)
	public String jobSeekerLogOut(){
			if(httpSession!=null)
			{
				httpSession.removeAttribute("username");
				httpSession.removeAttribute("email");
				httpSession.removeAttribute("education");
				httpSession.removeAttribute("firstName");
				httpSession.removeAttribute("lastName");
				httpSession.removeAttribute("education");
				httpSession.removeAttribute("skills");
				httpSession.removeAttribute("workExperience");
				httpSession.removeAttribute("picture");
				//System.out.println("removed username:");
				httpSession.invalidate();
				
			}
			return "redirect:/jobseeker/login";
		}
		
	
	//PROFILE - UPDATE
	@RequestMapping(value="/jobseeker/{username}/profile", method=RequestMethod.GET)
	public String getJobSeekerProfile(@PathVariable String username,ModelMap map){
		JobSeeker jobSeeker = jobSeekerService.findByUsername(username);
		httpSession.setAttribute("jobSeeker",jobSeeker);
		map.addAttribute("username", username);
		map.addAttribute("email",jobSeeker.getEmail());
		map.addAttribute("firstName",jobSeeker.getFirstName());
		map.addAttribute("lastName",jobSeeker.getLastName());
		map.addAttribute("selfIntroduction",jobSeeker.getSelfIntroduction());
		map.addAttribute("education",jobSeeker.getEducation());
		map.addAttribute("skills",jobSeeker.getSkills());
				
		return "jobseeker-profile";
	
}
	@RequestMapping("/jobseeker/{username}/profile")
	public void updateProfile(HttpServletRequest request){
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String introduction=request.getParameter("selfIntroduction");
		String education=request.getParameter("education");
		//String 
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
