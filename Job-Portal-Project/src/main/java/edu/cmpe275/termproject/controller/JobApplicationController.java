package edu.cmpe275.termproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmpe275.termproject.model.JobApplication;
import edu.cmpe275.termproject.model.JobSeeker;
import edu.cmpe275.termproject.service.JobApplicationService;
import edu.cmpe275.termproject.service.JobSeekerService;

@Controller
@MultipartConfig
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;

	@Autowired
	JobSeekerService jobSeekerService;

	
	// APPLY FOR JOB
	// REQUIRED job id and job seeker username
	@RequestMapping(value="/positions/applyjob",method=RequestMethod.POST)
	public String applyJob(HttpServletRequest request, ModelMap map) throws ParseException{
		
		String jobId = request.getParameter("jobId");
		String email = request.getParameter("email");
		String profile = request.getParameter("profile");
		String resume = request.getParameter("resume");
		String resumeData = request.getParameter(jobId);
	    byte[] by = null;

		System.out.println("inside applyJob");
		System.out.println("jobid "+jobId);
		System.out.println("email "+email);
		System.out.println("profile "+profile);
		System.out.println("resume "+resume);
		System.out.println("resumeData "+resumeData);
		
		if(resume.equals("true")){
			try {
				Part resumeFile = request.getPart(jobId);
			    System.out.println("file "+resumeFile);
			    String fileName = Paths.get(resumeFile.getSubmittedFileName()).getFileName().toString(); 
			    System.out.println("fileName "+fileName);
			    InputStream fileContent = resumeFile.getInputStream();
			    System.out.println("fileContent "+fileContent);
			    
			    by = new byte[1000000];
			    
			    int i = 0, j = 0;

			    while((i = fileContent.read())!=-1 && i<1000000) {
			        
		            
		            by[j] = (byte)i;
		            
		            // prints character
		            System.out.print(by[j++]);
					return jobApplicationService.applyJob(jobId, email, by, "none");
		         }
			} catch (Exception e) {
				System.out.println("inside applyJob catch(");
				e.printStackTrace();
			} 
		}
		else{
			return jobApplicationService.applyJob(jobId, email, null, "withprofile");
		}
		/*
		if(jobApplicationService.checkTotalPendingApplications(email)>=5){
			System.out.println("more than 5 applications");
			return "error";
		}

		if(jobApplicationService.checkIfApplicationPending(jobId, email)){
			System.out.println("pending applications");
			return "error";
		}
		*/
		return "error";
	}	

	// GEt applicants for a particular job
	//  REquired jobId
	@RequestMapping(value="/positions/{jobId}/applicants",method=RequestMethod.GET)
	public String findApplicants(@PathVariable String jobId, 
			HttpServletRequest request, ModelMap map) throws ParseException{
		
		jobApplicationService.findApplicants(jobId);
		
		return "success";
	}	

	@RequestMapping(value="/jobseeker/{username}/applications", method = RequestMethod.GET)
	public String viewUserApplications(@PathVariable String username, ModelMap map){
		
		System.out.println("inside viewUserApplications");
		System.out.println("username "+username);
		
		JobSeeker applicant = jobSeekerService.findByUsername(username);
		
		List<JobApplication> userApplications = jobApplicationService.findApplications(applicant);
		
		for(JobApplication application : userApplications){
			System.out.println("inside loop");
			System.out.println("inside loop "+application.getId());
			System.out.println("inside loop"+application.getPostedOn());
			System.out.println("inside loop"+application.getProfile());
			System.out.println("inside loop"+application.getJobPosting());
			System.out.println("inside loop"+application.getResume());
			System.out.println("inside loop"+application.getStatus());
		}
		
		map.addAttribute("")
		return "jobseeker-applications";
	}
	
	
	
	
	
	
}