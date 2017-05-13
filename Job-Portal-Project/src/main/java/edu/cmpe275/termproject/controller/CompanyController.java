package edu.cmpe275.termproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
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
		Company company=new Company(name, website, logoImageUrl, address, description, email ,password);
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
	public ModelAndView getAllPositions(@PathVariable long companyId, @RequestParam String status){
		List<JobPosting> jobs=companyService.getAllPositions(companyId, status);
		return new ModelAndView("positions", "positions", jobs);
	}
	@RequestMapping("/company/{companyId}/positions/{positionId}")
	public void getPositionDetails(@PathVariable long companyId, @PathVariable long poisitionId){
		
	}
}