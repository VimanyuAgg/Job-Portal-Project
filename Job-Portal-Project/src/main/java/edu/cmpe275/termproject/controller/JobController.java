package edu.cmpe275.termproject.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.JobService;
@RestController
public class JobController {
	@Autowired
	CompanyService companyService;
	@Autowired
	JobService jobSerivce;
	@RequestMapping(value="/{companyId}/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@PathVariable int companyId, @RequestParam String title,@RequestParam String description,@RequestParam String responsibilites, @RequestParam String offliceLocation, @RequestParam String salary ){
		Company company=companyService.getCompany(companyId);
		System.out.println(company.getCompanyName()+" "+company.getDescription());
		JobPosting job=new JobPosting(title, description, responsibilites, offliceLocation, salary, company);
		JobPosting jobAdded =jobSerivce.addJob(job);
		if(jobAdded!=null)
			return (new ResponseEntity<String>(new JSONObject(jobAdded).toString(),HttpStatus.OK));
		else 
			return (new ResponseEntity<String>(generateErrorMessage("Some error ocured").toString(),HttpStatus.NOT_FOUND));

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
}
