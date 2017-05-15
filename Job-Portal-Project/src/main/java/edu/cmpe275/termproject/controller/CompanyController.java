package edu.cmpe275.termproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.JobSeekerService;
@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private JobSeekerService jobSeekerService;
	@Autowired
	HttpSession session;
	@RequestMapping(value="/company/register", method=RequestMethod.GET)
	public String getCreateCompanyView(){
		System.out.println("I am here");
		return "companyregistration";
	}
	@RequestMapping(value="/company/register", method=RequestMethod.POST)
	public String registerCompany( HttpServletRequest request, ModelMap map){
		System.out.println("I am in post");
		String name=request.getParameter("name"), 
				website=request.getParameter("website"), 
				logoImageUrl=request.getParameter("logoImageUrl"),
				address=request.getParameter("address"), 
				description=request.getParameter("description"), 
				email=request.getParameter("email"), 
				password=request.getParameter("password");
		Company company=new Company(name, website, logoImageUrl, address, description, email ,password,0, null);
		Company result=companyService.registerCompany(company);
		if(result!=null){
			return "redirect:/company/login";
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
	
	@RequestMapping(value="/company/login", method=RequestMethod.GET)
	public String getCompanyLoginPage(){
		
		System.out.println("inside getCompany()");
		
		return "companylogin";
	}
	@RequestMapping(value="/company/login", method=RequestMethod.POST)
	public String companyLogin(HttpServletRequest request, ModelMap map, RedirectAttributes redirectAttribute){
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println("emaillll::::"+email);
		long companyId=companyService.authenticateCompany(email, password);
		if(companyService.authenticateCompany(email, password)!=-100){
			session.setAttribute("email", email);
			session.setAttribute("companyId", companyId);
			//session.setAttribute("companyId", companyId);
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

				map.addAttribute("jobs", jobs);
				
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
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
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
	
	@RequestMapping(value="/company",method = RequestMethod.GET)
	public String getCompanyByNames(HttpServletRequest request){
		
		System.out.println("inside getCompanyByName()");
		String companyName = "askl," + request.getParameter("companyName");
		List<Company> companies = companyService.getCompanyByName(companyName);
		
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
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company=companyService.getCompany(companyId);
		map.addAttribute("company",company);
		return "companyprofile";
	}
	@RequestMapping(value="/company/{companyId}/edit", method=RequestMethod.GET)
	public String getCompanyEditPage(@PathVariable long companyId, ModelMap map){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company= companyService.getCompany(companyId);
		map.addAttribute("company", company);
		return "company-edit";
	}
	@RequestMapping(value="/company/{companyId}/edit", method=RequestMethod.POST)
	public String editCompany(@PathVariable long companyId, ModelMap map, HttpServletRequest request){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company = companyService.getCompany(companyId);
		company.setCompanyName(request.getParameter("name"));
		company.setWebsite(request.getParameter("website"));
		company.setAddress(request.getParameter("address"));
		company.setDescription(request.getParameter("description"));
		company.setLogoUrl(request.getParameter("logoUrl"));
		companyService.registerCompany(company);
		System.out.println("I am website:"+company.getWebsite());
		map.addAttribute("company",company);
		return "companyprofile";
	}
	@RequestMapping("/logout")
	public String logout(){
		if(session!=null){
			System.out.println("I am removing email");
			session.removeAttribute("email");
			System.out.println("I am removing company");
			session.removeAttribute("companyId");
			System.out.println("I am invalidating");
			session.invalidate();
		}
		return "redirect:/company/login";
	}

}