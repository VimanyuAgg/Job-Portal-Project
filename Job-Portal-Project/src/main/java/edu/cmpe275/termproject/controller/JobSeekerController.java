//@ModelAttribute("workExperience") String workExperience,
//										 @ModelAttribute("education") String education,
//										 @ModelAttribute("skills") String skills,
//										 @ModelAttribute("password") String password,
//										 @ModelAttribute("authCode") String authCode,
//										 @ModelAttribute("email") String email,

package edu.cmpe275.termproject.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	public String createJobSeeker(HttpServletRequest request, RedirectAttributes redirectAttribute) throws GeneralSecurityException, IOException, ServletException{
		
		//System.out.println("Upload care ---"+request.getParameter("my_pic"));
		
//		String p = request.getParameter("picture");
//		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//		System.out.println("printing picture: "+ p);
//		if(request.getParameter("picture") != null){
//			System.out.println("picture found !");
//			try{
//				System.out.println("try-1");
//				Part filePart = request.getPart("picture");
//				System.out.println("try-2");
//				InputStream fileContent = filePart.getInputStream();
//				System.out.println("try-3");
//				//File uploads = new File(properties.getProperty("upload.location"));
//				//File uploads = new File("/");
//				OutputStream outputStream = new FileOutputStream(new File("/"));
//				System.out.println("try-4");
//				int r = 0;
//	            byte[] byteStream = new byte[32768];
//	            
//	            while(fileContent.read(byteStream) != -1){
//	            	outputStream.write(byteStream, 0, r);
//	            }
//	            System.out.println("try-5");
//			}
//			catch (Exception e){
//				e.printStackTrace();
//			}
			
			
//			System.out.println("picture found !");
//			 DiskFileItemFactory factory = new DiskFileItemFactory();
//			 factory.setSizeThreshold(1024 * 1024 * 2);
//			 factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//			 ServletContext context = request.getServletContext();
//			 String uploadFolder = context.getRealPath("/") + File.separator + "data";
//			 ServletFileUpload upload = new ServletFileUpload(factory);
//			 upload.setSizeMax(1024 * 1024);
//			 try {
//				 List items = upload.parseRequest(request);
//		            Iterator iter = items.iterator();
//		            while (iter.hasNext()) {
//		                FileItem item = (FileItem) iter.next();
//
//		                if (!item.isFormField()) {
//		                    String fileName = new File(item.getName()).getName();
//		                    String filePath = uploadFolder + File.separator + fileName;
//		                    File uploadedFile = new File(filePath);
//		                    System.out.println(filePath);
//		                    // saves the file to upload directory
//		                    item.write(uploadedFile);
//		                }
//		            }
//			 }
		
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
	public String jobSeekerLogin(@ModelAttribute ("username") String username)					{
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
	public String jobSeekerDashBoard(@PathVariable String username, ModelMap map){
		
		JobSeeker jobSeeker = jobSeekerService.getJobSeeker(username);
		
		
		System.out.println("Inside GET Jobseeker dashboard");
		System.out.println("username: "+username);
		System.out.println("httpsession getAttribute: "+httpSession.getAttribute(username));
		System.out.println("httpSession getAttString: "+httpSession.getAttribute("username"));

		if(httpSession.getAttribute("username") == null || !httpSession.getAttribute("username").equals(username)){
			return "redirect:/jobseeker/login";
		}

		map.addAttribute("topJobs",jobService.getTop10NewJobListings());
		map.addAttribute("firstName", jobSeeker.getFirstName());
		map.addAttribute("lastName", jobSeeker.getLastName());
		map.addAttribute("picture",jobSeeker.getPicture());
		map.addAttribute("selfIntroduction", jobSeeker.getSelfIntroduction());

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
	@RequestMapping(value="/jobseeker/{username}/update", method=RequestMethod.POST)
	public String updateProfile(@PathVariable String username,HttpServletRequest request, ModelMap map) throws GeneralSecurityException, IOException{
		System.out.println("inside update post");
		String picture = request.getParameter("picture");
		String firstName=request.getParameter("firstName");
		System.out.println("pictureeeeeeeee:"+picture+":bb");
		if(picture != null && !picture.isEmpty()){
			 picture= request.getParameter("picture");
		}
		
		String lastName=request.getParameter("lastName");
		String introduction=request.getParameter("selfIntroduction");
		String education=request.getParameter("education");
		String skills=request.getParameter("skills");
		String workExperience=request.getParameter("workExperience");
		String password=request.getParameter("password");
		JobSeeker jobSeeker=jobSeekerService.getJobSeeker(username);
		if(!SecurityConfig.decrypt(jobSeeker.getPassword()).equals(password)){
			return "redirect:/jobseeker/{username}/update/error";
		}
		if(picture != null && !picture.isEmpty()){
			jobSeeker.setPicture(picture);
		}
		jobSeeker.setFirstName(firstName);
		jobSeeker.setLastName(lastName);
		jobSeeker.setSelfIntroduction(introduction);
		jobSeeker.setEducation(education);
		jobSeeker.setSkills(skills);
		jobSeeker.setWorkExperience(workExperience);
		jobSeekerService.updateJobSeeker(jobSeeker);
		if(map.get("errorMessage")!=null){
			map.addAttribute("errorMessage","");
		}
		System.out.println("updated");

		return "redirect:/jobseeker/"+username+"/dashboard";
	}
	
	//VIEW INTERESTED JOBS	
	@RequestMapping(value="/jobseeker/{username}/viewInterestedJobs",method=RequestMethod.GET)
	public String viewAllInterestedJobs(@PathVariable("username") String username, ModelMap map){
		if(httpSession.getAttribute("username") == null){
			return "redirect:/jobseeker/login";
		}
		JobSeeker jobSeeker = jobSeekerService.getJobSeeker(username);
		System.out.println("Printing interested list");
		for(int i=0;i<jobSeeker.getInterestedList().size();i++){
			System.out.println(jobSeeker.getInterestedList().get(i).getJobId());
			
		}
		map.addAttribute("interestedJobs",jobSeeker.getInterestedList());
		map.addAttribute("picture",jobSeeker.getPicture());
		return "jobseeker-interestedjobs";
		
	}
	
	@RequestMapping("/jobseeker/{username}/update/error")
	public String jobSeekerUpdate(ModelMap map)
	{
		
		map.addAttribute("errorMessage","Incorrect Password");
		return "jobseeker-profile";	
		
	}
	
	@RequestMapping(value="/jobseeker/markInterested", method=RequestMethod.POST)
	public ResponseEntity<String> markApplicationAsInterested( HttpServletRequest request){
		
		String jobId = request.getParameter("jobId");
		String userName = (String) httpSession.getAttribute("username");
		String result = jobService.markApplicationAsInterested(jobId,userName);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		String resultJson = "{\"result\":";
		if(result.equals("You have already applied to this job!")){
			resultJson += "\""+result+"\"}";
		}
		else if(result.equals("This job is already in your interest list")){
			resultJson +="\""+result+"\"}";
		}
		else if(result.equals("Marked as interested")){
			resultJson +="\""+result+"\"}";
		}
		return new ResponseEntity<String>(resultJson, responseHeaders, HttpStatus.CREATED);
		
		
	}
	
	@RequestMapping(value="/jobseeker/markUninterested", method=RequestMethod.POST)
	public String markApplicationAsUninterested( HttpServletRequest request){
		
		String jobId = request.getParameter("jobId");
		String userName = (String) httpSession.getAttribute("username");
		String result = jobService.markApplicationAsUninterested(jobId,userName);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		String resultJson = "{\"result\":";
		if(result.equals("Marked as uninterested")){
			resultJson += "\""+result+"\"}";
		}
		else if(result.equals("This job doesn't exist in interested list")){
			resultJson +="\""+result+"\"}";
		}
//		return new ResponseEntity<String>(resultJson, responseHeaders, HttpStatus.CREATED);
		return "redirect:/jobseeker/"+userName+"/viewInterestedJobs";	
	}	
}