package edu.cmpe275.termproject.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmpe275.termproject.model.JobApplication;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.model.JobSeeker;
import edu.cmpe275.termproject.service.JobApplicationService;
import edu.cmpe275.termproject.service.JobSeekerService;
import edu.cmpe275.termproject.service.JobService;

@Controller
@MultipartConfig
public class JobApplicationController {

	@Autowired
	JobApplicationService jobApplicationService;

	@Autowired
	JobService jobPostingService;

	@Autowired
	JobSeekerService jobSeekerService;

	@Autowired
	HttpSession session;
	
	// APPLY FOR JOB
	// REQUIRED job id and job seeker username
	@RequestMapping(value="/positions/applyjob",method=RequestMethod.POST)
	public String applyJob(HttpServletRequest request, ModelMap map) throws ParseException{
		System.out.println("session: "+session);
		if(session == null)
		{
			return "redirect:/jobseeker/login";
		}
		
		String jobId = request.getParameter("jobId");
		String email=(String) session.getAttribute("email");
		System.out.println("Line 49");
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
		
		if(resumeData!=null){
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
					return jobApplicationService.applyJob(jobId, email, by, "Resume");
		         }
			} catch (Exception e) {
				System.out.println("inside applyJob catch(");
				e.printStackTrace();
			} 
		}
		else{
			return jobApplicationService.applyJob(jobId, email, null, "Profile");
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
	@RequestMapping(value="/positions/applicants",method=RequestMethod.GET)
	public String findApplicants(HttpServletRequest request, ModelMap map) throws ParseException{
		System.out.println("session: "+session);
		
		
		
		if(session == null)
		{
			return "redirect:/company/login";
		}

		System.out.println("inside findApplicants");
		
		String jobId = request.getParameter("jobId");
		System.out.println("jobId "+jobId);
		
		JobPosting job = jobPostingService.getJob(jobId);
		
		if(job != null) System.out.println("inside found job");
		
		List<JobApplication> applications = jobApplicationService.findApplicants(jobId);
		
		List<JobSeeker> applicants = new ArrayList<JobSeeker>();
		
		for(JobApplication application : applications){
			applicants.add(application.getApplicant());
		}
		
		for(JobSeeker applicant : applicants){
			System.out.println("inside loop");
			System.out.println(" "+applicant.getAuthenticationCode());
			System.out.println(" "+applicant.getEducation());
			System.out.println(" "+applicant.getEmail());
			System.out.println(" "+applicant.getFirstName());
			System.out.println(" "+applicant.getJsid());
			System.out.println(" "+applicant.getLastName());		
			System.out.println(" "+applicant.getPassword());		
			System.out.println(" "+applicant.getSkills());		
			System.out.println(" "+applicant.getUsername());		
			System.out.println(" "+applicant.getWorkExperience());		
			System.out.println(" "+applicant.getApplicationsList());		
		}

		System.out.println("returning ");
		map.addAttribute("applications",applications);
		map.addAttribute("applicants", applicants);
		map.addAttribute("logoImageUrl", job.getJobPostedByCompany().getLogoUrl());
		map.addAttribute("website", job.getJobPostedByCompany().getAddress());
		map.addAttribute("address", job.getJobPostedByCompany().getWebsite());
		
		return "jobapplicants";
	}	
	
	@RequestMapping(value="/jobseeker/app/{email}",method=RequestMethod.GET)
	public String viewUserApps(@PathVariable String email, HttpServletRequest request, ModelMap map){
		System.out.println("session: "+session);
		if(session == null)
			
		{
			return "redirect:/jobseeker/login";
		}
		//jobApplicationService.findApplicants(jobId);
		System.out.println("inside viewUserApps() checked");
		System.out.println("email "+email);
		
		JobSeeker applicant = jobSeekerService.findByEmail(email +".com");
		
		if(applicant != null) System.out.println("applicant found");
		else System.out.println("applicant not found");
		
		List<JobApplication> userApplications = jobApplicationService.findApplications(applicant);
		
		for(JobApplication application : userApplications){
			System.out.println("inside loop");
			System.out.println("inside loop "+application.getId());
			System.out.println("inside loop"+application.getPostedOn());
			System.out.println("inside loop"+application.getProfile());
			System.out.println("inside loop"+application.getJobPosting());
			
			if(application.getProfile().equals("Resume")){
				try{
//					FileOutputStream fos = new FileOutputStream("/file.txt");
//					fos.write(application.getResume());
//					fos.close();
				}catch(Exception e){
					System.out.println("Exception in viewUserApps");
				}
			}
			
			//map.addAttribute("applications", userApplications);
			
			System.out.println("inside loop"+application.getResume());
			System.out.println("inside loop"+application.getStatus());
		}
		
		System.out.println("application size "+userApplications.size());
		
		map.addAttribute("applications", userApplications);
		return "jobseeker-all-applications";
	}	
	
	@RequestMapping(value="/updateApp",method=RequestMethod.GET)
	public String updateApplications(HttpServletRequest request, ModelMap map){
		
		System.out.println("inside updateApplications()");
		
		String cancel = request.getParameter("cancel"); //cancel is comma separated list of application id
		String reject = request.getParameter("reject");
		String email = request.getParameter("email");

		System.out.println("cancel "+cancel);
		System.out.println("reject "+reject);
		System.out.println("email "+email);
		
		if(cancel != null && !cancel.equals("")){
			System.out.println("inside cancel");
			jobApplicationService.updateApplications(cancel, "Cancel");
		}
		
		if(reject != null && !reject.equals("")){
			System.out.println("inside reject");
			jobApplicationService.updateApplications(reject, "Reject");
		}
		
		//Need to add email service
		return "redirect:/jobseeker/applications/"+email;
	}	
	@RequestMapping("/positions/applicants/cancel")
	public String companyCancelApplication(ModelMap mop, HttpServletRequest request){
		String applicationId=request.getParameter("applicationId");
		boolean result=jobApplicationService.companyCancelApplication(applicationId);
		if(result){
			//Email the Candidate the status - 7G
			JobApplication application=jobApplicationService.getApplication(applicationId);
			String email=application.getApplicant().getEmail();
			//Email the Candidate the status - 7G
			return "redirect:/company/"+session.getAttribute("companyId")+"/welcome";
			}
		else{
			mop.addAttribute("errorMessage","Cannot cancel an application in terminal state");
			return "error";
		}
	}
}