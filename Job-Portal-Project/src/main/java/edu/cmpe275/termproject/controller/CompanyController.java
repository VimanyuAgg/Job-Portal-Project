package edu.cmpe275.termproject.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.cmpe275.termproject.emailService.PasswordSendingEmail;
import edu.cmpe275.termproject.emailService.RegistrationEmail;
import edu.cmpe275.termproject.emailService.WelcomeEmail;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobApplication;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.JobSeekerService;
import edu.cmpe275.termproject.service.JobService;
import edud.cmpe275.termproject.websecurity.SecurityConfig;
@Controller

public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private JobSeekerService jobSeekerService;
	@Autowired
	private JobService jobPostingService;
	@Autowired
	HttpSession session;
	@RequestMapping(value="/company/register", method=RequestMethod.GET)
	public String getCreateCompanyView(){
		System.out.println("I am here");
		return "companyregistration";
	}

	@RequestMapping(value="/in", method=RequestMethod.GET)
	public String getHome(){
		
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
		public String redirectToHome(){
			System.out.println("In REDITECT TO HOMMMEEEE");
			return "redirect:/in";
		}
	
	
	@RequestMapping(value="/company/register", method=RequestMethod.POST)
	public String registerCompany( HttpServletRequest request, ModelMap map, RedirectAttributes redirectAttribute) throws UnsupportedEncodingException, GeneralSecurityException{
		System.out.println("I am in post");
		String name=request.getParameter("name"), 
				website=request.getParameter("website"), 
				logoImageUrl=request.getParameter("logoImageUrl"),
				//photo=request.getParameter("clogo"),
				address=request.getParameter("address"), 
				description=request.getParameter("description"), 
				email=request.getParameter("email"), 
				password=request.getParameter("password");
		String authenticationCode_String = RegistrationEmail.generateAuthCode();        
		RegistrationEmail.registrationEmailTrigger(email, authenticationCode_String);
		
		System.out.println("inside registerCompany#################");
		//System.out.println("photo string "+photo);
		
		
		redirectAttribute.addFlashAttribute("name",name);
		redirectAttribute.addFlashAttribute("email",email);
		redirectAttribute.addFlashAttribute("isRedirected","true");
		
		Company company=new Company(name, website, logoImageUrl, address, description, email ,password,0, null);
		Company result=companyService.registerCompany(company);
		companyService.setAuthCode(authenticationCode_String, email);
		
		if(result!=null){
			return "redirect:/company/authentication";
		}else{			
			return "redirect:/company/register/error";
		}
	}
	@RequestMapping(value="/company/register/error", method=RequestMethod.GET)
	public String getCreateCompanyViewError(ModelMap map){
		System.out.println("I am here");
		map.addAttribute("errorMessage", "A company with the same email id already exists");
		return "companyregistration";
	}
	
	//AUTHENTICATION - GET
	@RequestMapping(value="/company/authentication", method=RequestMethod.GET)
	private String codeAuthenticationGET(@ModelAttribute ("name") String name,
										 @ModelAttribute("email") String email,
			 @ModelAttribute("isRedirected") String isRedirected){

			if (("true").equals(isRedirected)){
				return "company-code-authentication";
			}
			else{
				return "redirect:/company/login";

			}
	}
	
	//AUTHENTICATION POST
	@RequestMapping(value="/company/authentication", method = RequestMethod.POST)
	private String codeAuthenticationPOST(HttpServletRequest request, RedirectAttributes redirectAttribute) throws GeneralSecurityException, IOException{
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		System.out.println("name: "+name);
		System.out.println("email: "+email);
		String passCode = request.getParameter("codeVerification");
		Company userExists = companyService.getCompany(email);
		System.out.println("Company Name: "+name);
		String authCode = companyService.getCompany(email).getAuthenticationCode();
		System.out.println("Email code: "+authCode);
		System.out.println("User code: " +passCode);
		if(userExists != null && passCode.equals(authCode)){
			redirectAttribute.addFlashAttribute("username","Thank you for registering with us, "+name);
			companyService.saveCompanytoDAO(email);
			WelcomeEmail.welcomeEmailTrigger(companyService.getCompany(email).getEmail(), 
											 companyService.getCompany(email).getCompanyName());
			PasswordSendingEmail.deliverPasswordEmailCompany(companyService.getCompany(email).getEmail(), 
					companyService.getCompany(email).getCompanyName(), SecurityConfig.decrypt(companyService.getCompany(email).getPassword()));
			//System.out.println("Jobseeker "+firstName+ " saved to DB");
			return "redirect:/company/login";
		}
		else {
			redirectAttribute.addFlashAttribute("name", name);
			redirectAttribute.addFlashAttribute("email", email);
			redirectAttribute.addFlashAttribute("isRedirected","true");
			redirectAttribute.addFlashAttribute("isBadOTP","true");
			redirectAttribute.addFlashAttribute("badOTP","Sorry, the OTP you provided is incorrect");
			
			return "redirect:/company/authentication/error";

		}
		
		
	}
	
	//AUTHENTICATION - GET ERROR
		@RequestMapping(value="/company/authentication/error", method=RequestMethod.GET)
		private String codeAuthenticationGETError(@ModelAttribute ("name") String name,
											 @ModelAttribute("email") String email,
											 @ModelAttribute("isRedirected") String isRedirected,
											 @ModelAttribute("badOTP") String badOTP,
											 @ModelAttribute("isBadOTP") String isBadOTP){

				if (("true").equals(isRedirected)){
					return "company-code-authentication";
				}
				else{
					return "redirect:/company/login";

				}
		}
	@RequestMapping(value="/company/login", method=RequestMethod.GET)
	public String getCompanyLoginPage( @ModelAttribute("username") String name){
		
		System.out.println("inside getCompany()");
		
		return "companylogin";
	}
	@RequestMapping(value="/company/login", method=RequestMethod.POST)
	public String companyLogin(HttpServletRequest request, ModelMap map, RedirectAttributes redirectAttribute) throws GeneralSecurityException, IOException{
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println("emaillll::::"+email);
		long companyId=companyService.authenticateCompany(email, password);
		if(companyService.authenticateCompany(email, password)!=-100 && companyService.getCompany(email).isVerified()){
			session.setAttribute("email", email);
			session.setAttribute("companyId", companyId);
			//session.setAttribute("companyId", companyId);
			Company company=companyService.getCompany(companyId);
			session.setAttribute("companyName", company.getCompanyName());
			return "redirect:/company/"+companyId+"/welcome";
			}
		else{
				redirectAttribute.addFlashAttribute("notFoundText","Sorry, Email/password is invalid");
				String isNotFound = "true";
				redirectAttribute.addFlashAttribute("isNotFound",isNotFound);
				return "redirect:/company/login/error"; 
			}
	}
	//LOGIN - GET ERROR
			@RequestMapping(value="/company/login/error", method=RequestMethod.GET)
			public String jobSeekerLogin(@ModelAttribute ("email") String email,
										 @ModelAttribute ("isNotFound") String isNotFound,
										 @ModelAttribute ("notFoundText") String notFoundText)
										
			{
				
				
				return "companylogin";	
				
			}
	@RequestMapping("/company/{companyId}/welcome")
	public String companyLandingPage(@PathVariable long companyId, 
			HttpServletRequest request, ModelMap map){
		
		System.out.println("inside companyLandingPage");
		System.out.println("companyId "+companyId);
		if(session.getAttribute("companyId")!=null){
			System.out.println("Not null");
			System.out.println("Session comapny Id:" + session.getAttribute("companyId"));
			System.out.println("original company Id:" + String.valueOf(companyId));
			String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
			if(sessionCompanyId.equals(String.valueOf(companyId))){
				System.out.println("Not equal");
				List<JobPosting> result = new ArrayList<>();
				List<JobPosting> jobs = companyService.getAllPositions(companyId);
				System.out.println("Line 186"+jobs.size());
				
				String open = request.getParameter("open");
				String filled = request.getParameter("filled");
				String cancelled = request.getParameter("cancelled");
				
				System.out.println("open "+open);
				System.out.println("filled "+filled);
				System.out.println("cancelled "+cancelled);
				
				if(jobs == null) return "error";
				System.out.println("jobs size "+jobs.size());

				for(JobPosting job : jobs){
					System.out.println("inside for-loop");
					System.out.println("job status "+job.getJobStatus());
					if(open != null && open.length() != 0 && 
							job.getJobStatus().equals("Open")){
						System.out.println("inside open");
						result.add(job);
					}
					else if(filled != null && filled.length() != 0 && 
							job.getJobStatus().equals("Filled")){
						System.out.println("inside filled");
						result.add(job);
					}
					else if(cancelled != null && cancelled.length() != 0 && 
							job.getJobStatus().equals("Cancelled")){
						System.out.println("inside cancelled");
						result.add(job);
					}
					else if( (open == null || open.length() == 0)
							&& (filled == null || filled.length() == 0)
							&& (cancelled == null || cancelled.length() == 0)){
						System.out.println("inside else");
						result.add(job);
					}
					System.out.println(" "+job.getJobId());
//					System.out.println(" "+job.getJobTitle());
//		     		System.out.println(" "+job.getJobResponsibilities());
//					System.out.println(" "+job.getJobDescription());
//		   			System.out.println(" "+	job.getJobSalary());
//					System.out.println(" "+job.getPostedOn());
//					System.out.println(" "+job.getEligibility());
//					System.out.println(" "+job.getJobLocation());
					System.out.println(" "+job.getJobStatus());
//					System.out.println(" "+job.getTempSize());
//					System.out.println(" "+job.getApplicants());
//					System.out.println(" "+job.getJobPostedByCompany());
				}
				Company company = companyService.getCompany(companyId);
				map.addAttribute("jobs", result);
				map.addAttribute("logoImageUrl", company.getLogoUrl());
				map.addAttribute("description",company.getDescription());
				map.addAttribute("address",company.getAddress());
				map.addAttribute("website",company.getWebsite());
				System.out.println("open is ##### "+open);
				map.addAttribute("open", open);
				map.addAttribute("filled", filled);
				map.addAttribute("cancelled", cancelled);
				System.out.println("returning companyLandingPage");

				return "companylandingpage";
			}
			else{
				return "redirect:/company/login";
			}
		}
		return "redirect:/company/login";
	}
	@RequestMapping("/company/{companyId}/positions")
	public String getAllPositions(@PathVariable long companyId, @RequestParam(value = "status", required=false) String status, ModelMap map){
		List<JobPosting> jobs=companyService.getAllPositions(companyId, status);
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session==null || session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		System.out.println("Jobs Size:"+jobs.size());
		for(JobPosting job: jobs){
			System.out.println(job.getJobTitle());
		}
		map.addAttribute("positions", jobs);
		return "companyjobopenings";
	}
	
	
/*	  @RequestMapping(value = "/email/trigger", method = RequestMethod.POST)
	    public String triggerEmail() {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("Hello from Spring Boot Application");
	        message.setTo("sidanasparsh@gmail.com");
	        message.setFrom("sidanasparsh@gmail.com");
	        try {
	            mailSender.send(message);
	            return "{\"message\": \"OK\"}";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "{\"message\": \"Error\"}";
	        }
	    }*/
	
	@RequestMapping(value="/company/{companyId}/postjob",method = RequestMethod.GET)
	public String getCompanyByNames(@PathVariable long companyId, 
		HttpServletRequest request, ModelMap map){
		
		
		
		
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		
		Company company = companyService.getCompany(companyId);

		map.addAttribute("companyId", company.getCompanyId());
		map.addAttribute("website", company.getWebsite());
		map.addAttribute("logoImageUrl", company.getLogoUrl());
		map.addAttribute("address", company.getAddress());
		
		
		return "postjob";
	}
	
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String checkEmail(HttpServletRequest request, ModelMap map){
		System.out.println("inside getTestPage()");
		String email = request.getParameter("email");
		
		String findCompany = companyService.getCompanyByEmail(email);
		String findJobSeeker = jobSeekerService.getJobSeekerByEmail(email);
		System.out.println("jobseeker with this email is "+findJobSeeker);
		System.out.println("Company with this email is "+findCompany);
		if(findCompany != null || findJobSeeker != null){
			System.out.println("found email");
			return "emailFound";
		}

		return "noEmailFound";
	}	

	@RequestMapping(value="/company/{companyId}/profile", method=RequestMethod.GET)
	public String getCompanyProfilePage(@PathVariable long companyId, ModelMap map){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session==null ||session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company=companyService.getCompany(companyId);
		map.addAttribute("company",company);
		System.out.println("line 285 logo url"+company.getLogoUrl());

		//return "companyprofile";
		return "company-edit";

	}
	@RequestMapping(value="/company/{companyId}/edit", method=RequestMethod.GET)
	public String getCompanyEditPage(@PathVariable long companyId, ModelMap map){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company= companyService.getCompany(companyId);
		System.out.println("line 296 logo url"+company.getLogoUrl());
		map.addAttribute("company", company);
		return "company-edit";
	}
	@RequestMapping(value="/company/{companyId}/edit", method=RequestMethod.POST)
	public String editCompany(@PathVariable long companyId, ModelMap map, HttpServletRequest request) throws UnsupportedEncodingException, GeneralSecurityException{
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company = companyService.getCompany(companyId);
		company.setCompanyName(request.getParameter("name"));
		company.setWebsite(request.getParameter("website"));
		company.setAddress(request.getParameter("address"));
		System.out.println("Iam hereh LINE 401:"+request.getParameter("logoImageUrl"));
		company.setDescription(request.getParameter("description"));
		if(request.getParameter("logoImageUrl")!=null && request.getParameter("logoImageUrl").length()!=0)
			company.setLogoUrl(request.getParameter("logoImageUrl"));
		companyService.updateCompany(company);
		System.out.println("I am website:"+company.getWebsite());
		map.addAttribute("company",company);
		if(session.getAttribute("companyName") != null){
			session.setAttribute("companyName",company.getCompanyName());
		}
		return "redirect:/company/"+companyId+"/welcome";
	}
	
	@RequestMapping(value="/company/{companyId}/managejobs", method=RequestMethod.GET)
	public String manageJobs(@PathVariable long companyId, ModelMap map, HttpServletRequest request){
		
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		System.out.println("inside manageJobs");
		System.out.println("companyId "+companyId);
		
		List<JobPosting> jobs = companyService.getAllPositions(companyId);
		
		if(jobs == null) return "error";
		
		for(JobPosting job : jobs){
			System.out.println(" "+job.getJobId());
			System.out.println(" "+job.getJobTitle());
     		System.out.println(" "+job.getJobResponsibilities());
			System.out.println(" "+job.getJobDescription());
   			System.out.println(" "+	job.getJobSalary());
			System.out.println(" "+job.getPostedOn());
			System.out.println(" "+job.getEligibility());
			System.out.println(" "+job.getJobLocation());
			System.out.println(" "+job.getJobStatus());
			System.out.println(" "+job.getTempSize());
			System.out.println(" "+job.getApplicants());
			System.out.println(" "+job.getJobPostedByCompany());
		}
		
		Company company = companyService.getCompany(companyId);

		map.addAttribute("companyId", company.getCompanyId());
		map.addAttribute("website", company.getWebsite());
		map.addAttribute("logoImageUrl", company.getLogoUrl());
		map.addAttribute("address", company.getAddress());

		map.addAttribute("jobs", jobs);
		
		System.out.println("returning companyeditjobs");

		return "companyeditjobs";	
	}

	@RequestMapping("/logout")
	public String logout(){
		if(session!=null){
			System.out.println("I am removing email");
			session.removeAttribute("email");
			System.out.println("I am removing company");
			session.removeAttribute("companyId");
			session.removeAttribute("companyName");
			System.out.println("I am invalidating");
			session.invalidate();
		}
		return "redirect:/company/login";
	}

	
	@RequestMapping(value="/company/{companyId}/editjobs", method=RequestMethod.POST)
	public String editJobs(@PathVariable long companyId, ModelMap map, HttpServletRequest request){
		
		System.out.println("inside editJobs");
		
		String jobId = request.getParameter("jobId");
		
		System.out.println("jobId "+jobId);
		System.out.println("companyId "+companyId);
		
		JobPosting job = jobPostingService.getJob(jobId);

		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		if(job == null) return "error";
		map.addAttribute("jobs", companyService.getAllPositions(companyId));
		
		if(!(job.getJobStatus().equals("Cancelled") || 
				job.getJobStatus().equals("OfferRejcted") ||
				job.getJobStatus().equals("OfferAccepted") ||
				job.getJobStatus().equals("Rejected"))){
			job.setJobStatus("Cancelled");
			
			companyService.updateJob(job);
			//find all jobseeker email who have applied for this job
			//send email
			//send mail to interested people also
			List<JobApplication> applicationList = job.getApplicants();
			for(int i=0;i<applicationList.size();i++){
				String jobSeekerEmail = applicationList.get(i).getApplicant().getEmail();
				String jobSeekerFirstName = applicationList.get(i).getApplicant().getFirstName();
				String jobSeekerLastName = applicationList.get(i).getApplicant().getLastName();
				
			}
		}

		
		System.out.println(" "+job.getJobId());
		System.out.println(" "+job.getJobTitle());
 		System.out.println(" "+job.getJobResponsibilities());
		System.out.println(" "+job.getJobDescription());
		System.out.println(" "+	job.getJobSalary());
		System.out.println(" "+job.getPostedOn());
		System.out.println(" "+job.getEligibility());
		System.out.println(" "+job.getJobLocation());
		System.out.println(" "+job.getJobStatus());
		System.out.println(" "+job.getTempSize());
		System.out.println(" "+job.getApplicants());
		System.out.println(" "+job.getJobPostedByCompany());
	

		
		System.out.println("returning editJobs");

		return "companyeditjobs";	
	}
	
	@RequestMapping(value="/checkapplicant", method=RequestMethod.GET)
	public String checkApplicant(ModelMap map, HttpServletRequest request){
		
		System.out.println("inside checkApplicant()");
		
		String jobId = request.getParameter("jobid");
		
		System.out.println("jobId "+jobId);
		
		JobPosting job = jobPostingService.getJob(jobId);

		if(session.getAttribute("companyId") == null){
			return "redirect:/company/login";
		}
		
		if(job == null) return "error";
		
		List<JobApplication> applicationList = job.getApplicants();
		for(JobApplication jobApplication : applicationList){
			
			if(jobApplication.getStatus().equals("OfferAccepted")){
				return "cannot";
			}
			
		}
				
//		System.out.println(" "+job.getJobId());
//		System.out.println(" "+job.getJobTitle());
// 		System.out.println(" "+job.getJobResponsibilities());
//		System.out.println(" "+job.getJobDescription());
//		System.out.println(" "+	job.getJobSalary());
//		System.out.println(" "+job.getPostedOn());
//		System.out.println(" "+job.getEligibility());
//		System.out.println(" "+job.getJobLocation());
//		System.out.println(" "+job.getJobStatus());
//		System.out.println(" "+job.getTempSize());
//		System.out.println(" "+job.getApplicants());
//		System.out.println(" "+job.getJobPostedByCompany());
	
		System.out.println("returning checkApplicant");

		return "good";	
	}
}