package edu.cmpe275.termproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.JobService;

public class JobController {
		CompanyController cc=new CompanyController();
		@Autowired
		JobService jobSerivce;
	@RequestMapping("{companyId}/addjob/{number}")
	public ResponseEntity<?> addJob(@PathVariable int companyId, @PathVariable int number, @RequestParam String title,@RequestParam String description,@RequestParam String responsibilites, @RequestParam String offliceLocation, @RequestParam String salary ){
		Company company=(Company) cc.getCompany(companyId).getBody();
		JobPosting job=new JobPosting(title, description, responsibilites, offliceLocation, salary, company);
		jobSerivce.addJob(job);
		return null;
	}
}
