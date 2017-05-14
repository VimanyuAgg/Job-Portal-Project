package edu.cmpe275.termproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.CompanyService;
import edu.cmpe275.termproject.service.JobService;
@Controller
public class JobController {
	@Autowired
	CompanyService companyService;
	@Autowired
	HttpSession session;
	@Autowired
	JobService jobSerivce;
	
	@RequestMapping(value="/company/{companyId}/addjob",method=RequestMethod.GET)
	public String getJobAddPage(){
		return "postjob";
	}
/*	@RequestMapping(value="company/{companyId}/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@PathVariable int companyId, @RequestParam String title,@RequestParam String description,@RequestParam String responsibilites, @RequestParam String offliceLocation, @RequestParam String salary ){
		Company company=companyService.getCompany(companyId);
		System.out.println(company.getCompanyName()+" "+company.getDescription());
		JobPosting job=new JobPosting(title, description, responsibilites, offliceLocation, salary, company);
		JobPosting jobAdded =jobSerivce.addJob(job);
		if(jobAdded!=null)
			return (new ResponseEntity<String>(new JSONObject(jobAdded).toString(),HttpStatus.OK));
		else 
			return (new ResponseEntity<String>(generateErrorMessage("Some error ocured").toString(),HttpStatus.NOT_FOUND));

	}*/
	@RequestMapping(value="/company/{companyId}/addjob",method=RequestMethod.POST)
	public String addJob(@PathVariable long companyId, HttpServletRequest request, ModelMap map){
		
		Company company=companyService.getCompany(companyId);
		
		System.out.println(company.getCompanyName()+" "+company.getDescription());
		String title=request.getParameter("title"), description=request.getParameter("description"), responsibilites=request.getParameter("responsibilites"),
				offliceLocation=request.getParameter("location"), salary=request.getParameter("salary");
		long jobId = Long.parseLong(request.getParameter("jobId"));
		JobPosting job=new JobPosting(jobId,title, description, responsibilites, offliceLocation, salary, company,"MS");
		JobPosting jobAdded =jobSerivce.addJob(job);
		
		//long jobId = Long.parseLong(request.getParameter("jobId"));
		
		
		map.addAttribute("message", "Job has been posted!");
		
		if(jobAdded!=null)
			return "success";
		else 
			return "error";
	}
	
	@RequestMapping("/company/{companyId}/positions/{positionId}")
	public String getPositionDetails(@PathVariable long companyId, @PathVariable long positionId, ModelMap map){
		JobPosting job=jobSerivce.getJob(positionId);
		map.addAttribute("job",job);
		return "positiondetails";
	}
	@RequestMapping(value="/company/{companyId}/positions/{positionId}/edit", method=RequestMethod.GET)
	public String editPositionDetailsGet(@PathVariable long companyId, @PathVariable long positionId, HttpServletRequest request, ModelMap map){
		JobPosting job=jobSerivce.getJob(positionId);
		map.addAttribute("job",job);
		return "editpostion";
	}
	@RequestMapping(value="/company/{companyId}/positions/{positionId}/edit", method=RequestMethod.POST)
	public String editPositionDetails(@PathVariable long companyId, @PathVariable long positionId, HttpServletRequest request, ModelMap map){
	       String title=request.getParameter("title"), description=request.getParameter("description"), responsibilites=request.getParameter("responsibilites"),
				offliceLocation=request.getParameter("location"), salary=request.getParameter("salary"), status=request.getParameter("status");
		Company company=companyService.getCompany(companyId);
		JobPosting currentJob=jobSerivce.getJob(positionId);
		//String title=currentJob.getJobTitle(), description=currentJob.getJobDescription(), responsibilites=currentJob.getJobResponsibilities(),
		//		offliceLocation=currentJob.getJobLocation(), salary=currentJob.getJobSalary(), status=currentJob.getJobStatus();
		jobSerivce.removeJob(currentJob);
		System.out.println("Title:"+title);
		JobPosting job=new JobPosting(positionId, title, description, responsibilites, offliceLocation, salary, company,"MS");
		System.out.println("Description:"+job.getJobDescription());
		jobSerivce.addJob(job);
		map.addAttribute("job",job);
		return "positiondetails";
	}
	/*private JSONObject generateErrorMessage(String message) {
		// TODO Auto-generated method stub
		JSONObject json= new JSONObject();
		try {
			json.put("errorMesshage", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return json;
	}*/
	
	@RequestMapping(value="/positions",method=RequestMethod.GET)
	public String findPositions(HttpServletRequest request){
		
		System.out.println("inside findPositions()");
		String jobId = "-1,"+request.getParameter("jobId");
		List<JobPosting> positions = jobSerivce.getPositions(jobId);
		
		return null;
	}
}
