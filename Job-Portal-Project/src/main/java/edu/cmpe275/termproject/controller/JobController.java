package edu.cmpe275.termproject.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmpe275.termproject.emailService.JobAppliedEmail;
import edu.cmpe275.termproject.emailService.JobFieldsChangedEmail;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobApplication;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.model.JobSeeker;
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
	public String getJobAddPage(@PathVariable String companyId){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
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
	public String addJob(@PathVariable long companyId, HttpServletRequest request, ModelMap map) throws ParseException{
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		Company company=companyService.getCompany(companyId);
		
		System.out.println(company.getCompanyName()+" "+company.getDescription());
		String title=request.getParameter("title"), 
				description=request.getParameter("description"), 
				responsibilites=request.getParameter("responsibilities"),
				offliceLocation=request.getParameter("location"), 
				salary=request.getParameter("salary");
		String jobId =request.getParameter("jobId");
		JobPosting job=new JobPosting(jobId,title, description, responsibilites, offliceLocation, salary, company,"MS");
		JobPosting jobAdded =jobSerivce.addJob(job);
		System.out.println(company.getCompanyName()+""+company.getJobPostingList().size());
		//company.getJobPostingList().add(job);
		companyService.updateCompany(company);
		System.out.println(company.getCompanyName()+""+company.getJobPostingList().size());
		//long jobId = Long.parseLong(request.getParameter("jobId"));
		
		
		map.addAttribute("message", "Job has been posted!");
		
		if(jobAdded!=null)
			return "companyjoppost-success";
		else 
			return "error";
	}
	
	@RequestMapping("/company/{companyId}/positions/{positionId}")
	public String getPositionDetails(@PathVariable long companyId, @PathVariable String positionId, ModelMap map){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		JobPosting job=jobSerivce.getJob(positionId);
		if(job==null){
			map.addAttribute("errorMessage", "Job with ID "+ positionId +" doesn't exist");
			return "error";
		}
		map.addAttribute("job",job);
		return "positiondetails";
	}
	@RequestMapping(value="/company/{companyId}/positions/{positionId}/edit", method=RequestMethod.GET)
	public String editPositionDetailsGet(@PathVariable long companyId, @PathVariable String positionId, HttpServletRequest request, ModelMap map){
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}
		JobPosting job=jobSerivce.getJob(positionId);
		if(job==null){
			System.out.println("NULLLLLLLLLLLLL");
			map.addAttribute("errorMessage","No job with job id "+positionId+" exists");
			return "error";
		}
		map.addAttribute("job",job);
		
		System.out.println("eligibility "+job.getEligibility());
		System.out.println("desc "+job.getJobDescription());
		System.out.println("jobid "+job.getJobId());
		System.out.println(" "+job.getJobLocation());
		System.out.println(" "+job.getJobResponsibilities());
		System.out.println(" "+job.getJobSalary());
		System.out.println(" "+job.getJobStatus());
		System.out.println(" "+job.getJobTitle());
				
		Company company = companyService.getCompany(companyId);
		map.addAttribute("logoImageUrl", company.getLogoUrl());
		map.addAttribute("description",company.getDescription());
		map.addAttribute("address",company.getAddress());
		map.addAttribute("website",company.getWebsite());
		
		return "editpostion";
	}
	@RequestMapping(value="/company/{companyId}/positions/{positionId}/edit", method=RequestMethod.POST)
	public String editPositionDetails(@PathVariable long companyId, @PathVariable String positionId, HttpServletRequest request, ModelMap map) throws ParseException{
		
		System.out.println("inside editPositionDetails");
		
		String sessionCompanyId=String.valueOf(session.getAttribute("companyId"));
		
		if(session.getAttribute("companyId")==null || !sessionCompanyId.equals(String.valueOf(companyId))){
			return "redirect:/company/login";
		}   
		
		String title = request.getParameter("title"), 
	    		description=request.getParameter("description"), 
	    		responsibilites=request.getParameter("responsibilities"),
				offliceLocation=request.getParameter("location"), 
				salary=request.getParameter("salary"), 
				status=request.getParameter("status");
		
		JobPosting job=jobSerivce.getJob(positionId);
		
		if(job==null)
			return "error";
		System.out.println("Title:"+title);
		//JobPosting job=new JobPosting(positionId, title, description, responsibilites, offliceLocation, salary, company,"MS");
		System.out.println("Description:"+job.getJobDescription());
		job.setJobTitle(title);
		//job.setEligibility(eligibility);
		job.setJobDescription(description);
		job.setJobLocation(offliceLocation);
		job.setJobResponsibilities(responsibilites);
		job.setJobSalary(salary);
		job.setJobStatus(status);
		jobSerivce.addJob(job);
		map.addAttribute("job",job);
		List<JobApplication> applications=job.getApplicants();
		System.out.println(applications.size());
		if(applications!=null){
			List<JobSeeker> applicants=new ArrayList<JobSeeker>();
			for(JobApplication application:applications){
				applicants.add(application.getApplicant());
			}
			for(JobSeeker applicant: applicants){
				System.out.println("Check MEEE::::");
				System.out.println(applicant.getFirstName());
				String email=applicant.getEmail();
				// Mail The Applicant
				JobFieldsChangedEmail.somethingChangedInJobEmail(email, applicant.getFirstName(), applicant.getLastName(), 
											job.getJobId(),job.getJobTitle(), job.getJobPostedByCompany().getCompanyName());
			}
			
			//Sending mail to interestedApplicants
			for(JobSeeker js : job.getInterestedApplicants()){
				JobFieldsChangedEmail.somethingChangedInJobEmail_Interested(js.getEmail(), js.getFirstName(),
																		js.getLastName(),job.getJobId(), 
																		job.getJobTitle(), job.getJobPostedByCompany().getCompanyName());
			}
		}
		return  "redirect:/company/"+companyId+"/welcome";
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
	
		@RequestMapping(value="/positions", method=RequestMethod.POST)
		public String findPositionsOnSearchString(HttpServletRequest request, ModelMap map){
			String searchString = request.getParameter("searchString");
			System.out.println("Inside Positions POST for Search String !!");
			
			List<JobPosting> searchStringList = new ArrayList<JobPosting>();
			
			searchStringList.addAll(jobSerivce.findFromSearchString(searchString));
			
			System.out.println("searchStringList Size: "+searchStringList.size());
			
			map.addAttribute("positions", searchStringList);
			
			return "viewPositions";
			
		}
	
		//UNIVERSAL SEARCH
		@RequestMapping(value="/positions/universalsearch", method=RequestMethod.POST)
		public String universalSearch(HttpServletRequest request,ModelMap map){
			
			
			System.out.println("Inside universal search");
			String jobId = request.getParameter("jobId");
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String salary = null;
			
			String status = request.getParameter("status");
			String postedOn = request.getParameter("postedon");
			System.out.println("jobId: "+ jobId);
			System.out.println("title: "+title);
			System.out.println("location"+location);
			
			System.out.println("status: "+status);
			System.out.println("postedon: "+postedOn);
			
			List<JobPosting> positions_fields = new ArrayList<JobPosting>();
					
			positions_fields = jobSerivce.searchByFields(jobId, title, location, salary, status, postedOn);
			
			System.out.println("Field search positions gave "+positions_fields.size());
			int minimum = 0;
			int maximum = 0;
			try{
				minimum = Integer.valueOf(request.getParameter("min"));
			}
			catch(java.lang.NumberFormatException e){
				minimum=0;
			}
			//minimum = Integer.valueOf(request.getParameter("min"));
			System.out.println("min salary: "+minimum);
			try{
				maximum = Integer.valueOf(request.getParameter("max"));
			}
			catch(java.lang.NumberFormatException e){
				maximum=0;
			}
			//maximum = Integer.valueOf(request.getParameter("max"));
			System.out.println("max salary: "+maximum);
			
			List<JobPosting> salaryRangeJobsList = new ArrayList<JobPosting>();
			salaryRangeJobsList.addAll(jobSerivce.searchBySalaryRange(minimum, maximum));
			
			System.out.println("Salary RangeJobsList has size "+salaryRangeJobsList.size());
			positions_fields.addAll(salaryRangeJobsList); // ORing the salary range and field search
			
			System.out.println("After ORing positions_fields is now "+positions_fields.size());
			String searchString = request.getParameter("searchString");
			System.out.println(" for Search String !!");
			
			List<JobPosting> searchStringList = new ArrayList<JobPosting>();
			
			if(searchString !=null && !searchString.isEmpty()){
				searchStringList.addAll(jobSerivce.findFromSearchString(searchString));
				//ANDing here
				System.out.println("Beforing ANDing searchStringList.size() "+ searchStringList.size());
				searchStringList.retainAll(positions_fields);
				System.out.println("After ANDing searchStringList is now "+searchStringList.size());
				map.addAttribute("positions", searchStringList);
			}
			else{
				map.addAttribute("positions",positions_fields);
			}
			
			
			return "viewPositions";
		}
	
	// REQUIREMENT No 2
		@RequestMapping(value="/positions/searchByFields",method=RequestMethod.POST)
		public String findPositions(HttpServletRequest request, ModelMap map){
			
			System.out.println("inside findPositions()---GET");
			String jobId = request.getParameter("jobId");
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String salary = request.getParameter("salary");
			String status = request.getParameter("status");
			String postedOn = request.getParameter("postedon");
			System.out.println("jobId: "+ jobId);
			System.out.println("title: "+title);
			System.out.println("location"+location);
			System.out.println("salary: "+salary);
			System.out.println("status: "+status);
			System.out.println("postedon: "+postedOn);
			
			
		
			List<JobPosting> positions = new ArrayList<JobPosting>();
			positions = jobSerivce.searchByFields(jobId, title, location, salary, status, postedOn);
			//positions.get(0).getJobPostedByCompany().getCompanyName()
			map.addAttribute("positions", positions);
			return "viewPositions";
		}
		@RequestMapping("/positions/{positionId}")
		public String getJobDetails(@PathVariable String positionId,ModelMap map){
			JobPosting job=jobSerivce.getJob(positionId);
			map.addAttribute("job",job);
			return "job-details";
		}
		
		//SALARY SEARCH
		@RequestMapping(value="/positions/salarysearch", method=RequestMethod.POST)
		public String getJobsBySalary(HttpServletRequest request, ModelMap map){
			int minimum = Integer.valueOf(request.getParameter("min"));
			System.out.println("min salary: "+minimum);
			
			int maximum = Integer.valueOf(request.getParameter("max"));
			System.out.println("max salary: "+maximum);
			
			List<JobPosting> salaryRangeJobsList = jobSerivce.searchBySalaryRange(minimum, maximum);
			
			map.addAttribute("positions", salaryRangeJobsList);
			return "viewPositions";
		}
	
}
