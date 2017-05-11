package edu.cmpe275.termproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.service.CompanyService;

public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@RequestMapping("/company/register/{name}")
	public ResponseEntity<?> addCompany(@PathVariable String name, @RequestParam String website, @RequestParam String logoImageUrl, @RequestParam String address, @RequestParam String description ){
		Company company=new Company(name, website, logoImageUrl,address,description);
		return companyService.addCompany(company);
	}
	@RequestMapping("/{companyId}")
	public ResponseEntity<?> getCompany(@PathVariable int companyId){
		return companyService.getCompany(companyId);
	}
	//@RequestMapping("/company/addjob/")
	//public ResponseEntity<?> addJob(){
		
//	}
}
