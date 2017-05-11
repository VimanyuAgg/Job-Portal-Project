package edu.cmpe275.termproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.service.CompanyService;
@RestController
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@RequestMapping(value="/company/register", method=RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestParam String name, @RequestParam String website, @RequestParam String logoImageUrl, @RequestParam String address, @RequestParam String description ){
		System.out.println("I am here");
		Company company=new Company(name, website, logoImageUrl,address,description);
		return companyService.addCompany(company);
	}
	@RequestMapping("/company/{companyId}")
	public ResponseEntity<?> getCompany(@PathVariable long companyId){
		return companyService.getCompany(companyId);
	}
	//@RequestMapping("/company/addjob/")
	//public ResponseEntity<?> addJob(){
		
//	}
}
