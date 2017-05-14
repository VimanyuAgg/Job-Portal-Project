package edu.cmpe275.termproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		String name=request.getParameter("name"), website=request.getParameter("website"), logoImageUrl=request.getParameter("logoImageUrl"),
				address=request.getParameter("address"), description=request.getParameter("description"), email=request.getParameter("email"), password=request.getParameter("password");
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
	public String companyLogin(HttpServletRequest request){
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
		else
			return "redirect:/error";
	}
	@RequestMapping("/company/{companyId}/welcome")
	public String companyLandingPage(){
		return "companylandingpage";
	}
	@RequestMapping("/company/{companyId}/positions")
	public String getAllPositions(@PathVariable long companyId, @RequestParam(value = "status", required=false) String status, ModelMap map){
		List<JobPosting> jobs=companyService.getAllPositions(companyId, status);
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
		if(findCompany != null || findJobSeeker != null){
			System.out.println("found email");
			return "emailFound";
		}

		return "noEmailFound";
	}	
}