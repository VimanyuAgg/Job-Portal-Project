package edu.cmpe275.termproject.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.service.JobApplicationService;

@Controller
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;
	
	@RequestMapping(value="/profiles/{jobId}/applyjob",method=RequestMethod.POST)
	public String applyJob(HttpServletRequest request, ModelMap map) throws ParseException{
		
		String jobId = request.getParameter("jobId");
		String jobSeekerUsername = request.getParameter("jsid");
		
		String result = jobApplicationService.applyJob(jobId, jobSeekerUsername);
		
		return result;
	}
	
}
