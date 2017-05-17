package edu.cmpe275.termproject.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String addJob(@PathVariable long companyId, HttpServletRequest request, ModelMap map) throws ParseException{
		
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
			return "success";
		else 
			return "error";
	}
	
	@RequestMapping("/company/{companyId}/positions/{positionId}")
	public String getPositionDetails(@PathVariable long companyId, @PathVariable String positionId, ModelMap map){
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
		JobPosting job=jobSerivce.getJob(positionId);
		if(job==null){
			System.out.println("NULLLLLLLLLLLLL");
			map.addAttribute("errorMessage","No job with job id "+positionId+" exists");
			return "error";
			}
		map.addAttribute("job",job);
		return "editpostion";
	}
	@RequestMapping(value="/company/{companyId}/positions/{positionId}/edit", method=RequestMethod.POST)
	public String editPositionDetails(@PathVariable long companyId, @PathVariable String positionId, HttpServletRequest request, ModelMap map) throws ParseException{
	       String title=request.getParameter("title"), 
	    		   description=request.getParameter("description"), responsibilites=request.getParameter("responsibilities"),
				offliceLocation=request.getParameter("location"), salary=request.getParameter("salary"), status=request.getParameter("status");
		//Company company=companyService.getCompany(companyId);
		JobPosting job=jobSerivce.getJob(positionId);
		//String title=currentJob.getJobTitle(), description=currentJob.getJobDescription(), responsibilites=currentJob.getJobResponsibilities(),
		//		offliceLocation=currentJob.getJobLocation(), salary=currentJob.getJobSalary(), status=currentJob.getJobStatus();
		// currentJob= jobSerivce.removeJob(currentJob);
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
		}
		return  "redirect:/company/"+companyId+"/managejobs";
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
	
	
	// REQUIREMENT No 2
		@RequestMapping(value="/positions",method=RequestMethod.GET)
		public String findPositions(HttpServletRequest request, ModelMap map){
			
			System.out.println("inside findPositions()");
			String jobId = request.getParameter("jobId");
			String title = request.getParameter("title");
			String location = request.getParameter("location");
			String salary = request.getParameter("salary");
			String status = request.getParameter("status");
			String postedOn = request.getParameter("postedon");
//			
//				
//			
//			
//			if(jobId != null && jobId.length() != 0){
//				System.out.println("found jobId "+jobId);
//				jobId = request.getParameter("jobId") + ",";
//			}
//			
//			if(title != null && title.length() != 0){
//				System.out.println("found title "+title);
//				title = request.getParameter("title") + ",";
//			}
//			
//			if(location != null && location.length() != 0){
//				System.out.println("found location "+location);
//				location = request.getParameter("location") + ",";
//			}
//			
//			if(salary != null && salary.length() != 0){
//				System.out.println("found salary "+salary);
//				salary = request.getParameter("salary") + ",";
//			}
//			
//			if(status != null && status.length() != 0){
//				System.out.println("found status " +status);
//				status = request.getParameter("status") + ",";
//			}
//			
//			if(postedOn != null && postedOn.length() != 0){
//				System.out.println("found postedOn "+postedOn);
//				postedOn = request.getParameter("postedOn") + ",";
//			}
//			
//			List<JobPosting> positions = jobSerivce.getPositions(jobId, title, location, salary, status, postedOn);
			List<JobPosting> positions = new ArrayList<JobPosting>();
			positions = jobSerivce.searchByFields(jobId, title, location, salary, status, postedOn);
			map.addAttribute("positions", positions);
			return "viewPositions";
		}
		@RequestMapping("/positions/{positionId}")
		public String getJobDetails(@PathVariable String positionId,ModelMap map){
			JobPosting job=jobSerivce.getJob(positionId);
			map.addAttribute("job",job);
			return "job-details";
		}
		
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
