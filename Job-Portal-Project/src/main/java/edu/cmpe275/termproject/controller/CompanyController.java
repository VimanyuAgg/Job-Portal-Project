package edu.cmpe275.termproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.CompanyService;
@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	/*@RequestMapping(value="/company/register", method=RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestParam String name, @RequestParam String website, @RequestParam String logoImageUrl, @RequestParam String address, @RequestParam String description ){
		System.out.println("I am here");
		Company company=new Company(name, website, logoImageUrl,address,description);
		return companyService.addCompany(company);
	}*/
	@RequestMapping(value="/companyRegistration", method=RequestMethod.GET)
	public ModelAndView getCreateCompanyView(){
		System.out.println("I am here");
		int z=5;
		return new ModelAndView("companyRegistration","CompanyRegistration", z);
	}
	@RequestMapping(value="/companyRegistration", method=RequestMethod.POST)
	public String createCompany( HttpServletRequest request){
		System.out.println("I am in post");
		String name=request.getParameter("name"), website=request.getParameter("website"), logoImageUrl=request.getParameter("logoImageUrl"),
				address=request.getParameter("address"), description=request.getParameter("description"), email=request.getParameter("email"), password=request.getParameter("password");
		Company company=new Company(name, website, logoImageUrl, address, description, email ,password);
		companyService.addCompany(company);
		return "redirect:/companyCreated.html";
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
		if(companyService.authenticateCompany(email, password))
			return "welcome";
		else
			return "redirect:/error";
	}
	@RequestMapping("/company/{companyId}")
	public ResponseEntity<?> getCompany(@PathVariable long companyId){
	//	return companyService.getCompany(companyId);
		Company company=companyService.getCompany(companyId);
		if(company!=null)
			return new ResponseEntity<String>(new JSONObject(company).toString(),HttpStatus.OK);
		else
			return new ResponseEntity<String>(generateErrorMessage("Company Does not exist").toString(),HttpStatus.NOT_FOUND);
	}
	@RequestMapping("/company/{companyId}/positions")
	public ModelAndView getAllPositions(@PathVariable long companyId, @RequestParam String status){
		
		List<JobPosting> jobs=companyService.getAllPositions(companyId, status);
		return new ModelAndView("positions", "positions", jobs);
	}
	@RequestMapping("/company/{companyId}/positions/{positionId}")
	public void getPositionDetails(@PathVariable long companyId, @PathVariable long poisitionId){
		
	}
	private JSONObject generateErrorMessage(String message) {
		// TODO Auto-generated method stub
		JSONObject json= new JSONObject();
		try {
			json.put("errorMesshage", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return json;
	}
	//@RequestMapping("/company/addjob/")
	//public ResponseEntity<?> addJob(){
		
//	}
}