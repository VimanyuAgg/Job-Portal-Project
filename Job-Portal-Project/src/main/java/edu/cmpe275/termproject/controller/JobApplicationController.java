package edu.cmpe275.termproject.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.cmpe275.termproject.service.JobApplicationService;

@Controller
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;

	
	// APPLY FOR JOB
	// REQUIRED job id and job seeker username
	@RequestMapping(value="/positions/{jobId}/applyjob",method=RequestMethod.POST)
	public String applyJob(HttpServletRequest request, ModelMap map) throws ParseException{
		
		String jobId = request.getParameter("jobId");
		String email = request.getParameter("email");

		System.out.println("inside applyJob");
		System.out.println("jobid "+jobId);
		System.out.println("email "+email);

		
		if(jobApplicationService.checkTotalPendingApplications(email)>=5){
			System.out.println("more than 5 applications");
			return "error";
		}

		if(jobApplicationService.checkIfApplicationPending(jobId, email)){
			System.out.println("pending applications");
			return "error";
		}
		return jobApplicationService.applyJob(jobId, email);
	}	

	// GEt applicants for a particular job
	//  REquired jobId
	@RequestMapping(value="/positions/{jobId}/applicants",method=RequestMethod.GET)
	public String findApplicants(@PathVariable String jobId, 
			HttpServletRequest request, ModelMap map) throws ParseException{
		
		jobApplicationService.findApplicants(jobId);
		
		return "success";
	}	
}