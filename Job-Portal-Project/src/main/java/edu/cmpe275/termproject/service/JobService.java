package edu.cmpe275.termproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.cmpe275.termproject.dao.CompanyDAO;
import edu.cmpe275.termproject.dao.JobPostingDAO;
import edu.cmpe275.termproject.dao.JobSeekerDAO;
import edu.cmpe275.termproject.model.Company;
import edu.cmpe275.termproject.model.JobPosting;
import edu.cmpe275.termproject.model.JobSeeker;
@Service
public class JobService {
	@Autowired
	JobPostingDAO jobPostingDao;
	
	@Autowired
	JobSeekerDAO jobSeekerDAO;
	
	@Autowired
	CompanyDAO companyDAO;
	
	public JobPosting addJob(JobPosting job){
		jobPostingDao.save(job);
		return job;
	}	
	public JobPosting getJob(String jobId){
		return jobPostingDao.findByJobId(jobId);
	}
	public void removeJob(JobPosting job){
		jobPostingDao.delete(job);
	}
	
	public ArrayList<JobPosting> getTop10NewJobListings(){
		//ArrayList<JobPosting> jobList = new ArrayList<JobPosting>();

		PageRequest pr = new PageRequest(1,1);
		ArrayList<JobPosting> jobList = jobPostingDao.findTop10jobs();

		 System.out.println("inside job service - jobList size: "+jobList.size());
		 
		 for(int i=0;i<jobList.size();i++){
			 if(!jobList.get(i).getJobStatus().equals("open")){
				 jobList.remove(i);
			 }
		 }
		 System.out.println("open jobList size: "+jobList.size());
		return jobList;
	}
	
	public List<JobPosting> getPositions(String jobId, 
			String title, String location, String salary,
			String status, String postedOn) {
		
		System.out.println("inside getPositions()");
		
		List<String> jobTitleList = null;
		List<String> jobLocationList = null;
		List<String> jobSalaryList = null;
		List<String> jobStatusList = null;
		List<String> jobPostedOnList = null;
		int tempTitle = 1, 
				tempJobLocation = 1, tempJobSalary = 1,
				tempJobStatus = 1, tempPostedOn = 1;
		
		if(title != null && title.length() != 0){
			tempTitle = 0;
			jobTitleList = Arrays.asList(title.split(","));
		}
		
		if(location != null && location.length() != 0){
			tempJobLocation = 0;
			jobLocationList = Arrays.asList(location.split(","));
		}
		
		if(salary != null && salary.length() != 0){
			tempJobSalary = 0;
			jobSalaryList = Arrays.asList(salary.split(","));
		}
		
		if(status != null && status.length() != 0){
			tempJobStatus = 0;
			jobStatusList = Arrays.asList(status.split(","));
		}
		
		if(postedOn != null && postedOn.length() != 0){
			tempPostedOn = 0;
			jobPostedOnList = Arrays.asList(postedOn.split(","));
		}
		
		System.out.println("tempTitle "+tempTitle);
		System.out.println("tempJobLocation "+tempJobLocation);
		System.out.println("tempJobSalary "+tempJobSalary);
		System.out.println("tempJobStatus "+tempJobStatus);
		System.out.println("tempPostedOn "+tempPostedOn);

		List<JobPosting> positions = jobPostingDao.findJobs(jobTitleList, 
				jobLocationList, jobSalaryList, jobStatusList, jobPostedOnList, 
				tempTitle, tempJobLocation, tempJobSalary, tempJobStatus, tempPostedOn);
		
		//System.out.println("titles "+jobIdList);
		
		
		for(JobPosting position : positions){
			System.out.println("inside loop");
			System.out.println("getJobId  "+position.getJobId());
			System.out.println("getJobDescription "+position.getJobDescription());
			System.out.println("getJobTitle "+position.getJobTitle());
			System.out.println("getJobResponsibilities "+position.getJobResponsibilities());
			System.out.println("getJobLocation "+position.getJobLocation());
			System.out.println("getJobSalary "+position.getJobSalary());
			System.out.println("getJobStatus "+position.getJobStatus());
			System.out.println("getPostedOn "+position.getPostedOn());
			System.out.println("getEligibility "+position.getEligibility());
		}
				
		return positions;
	}
	public List<JobPosting> findFromSearchString(String searchString) {
		// TODO Auto-generated method stub
		String[] searchStringArray = searchString.split(" "); //Include regex to split by comma etc.
		List<JobPosting> searchStringLists = new ArrayList<JobPosting>();
		
		System.out.println("Size of searchStringArray is "+searchStringArray.length);
		
		for (String str : searchStringArray){
			System.out.println("searching based on: "+str);
			if(jobPostingDao.findByJobDescription(str).size() > 0){
				System.out.println("Match Found for Description");
				searchStringLists.addAll(jobPostingDao.findByJobDescription(str));
			}
			
			System.out.println("Searching for job ID");
			if(jobPostingDao.findByJobId(str) != null){
				System.out.println("Match Found for JobID");
				searchStringLists.add(jobPostingDao.findByJobId(str));
				
			}
			
			System.out.println("Searching for job location");
			if(jobPostingDao.findByJobLocation(str).size() > 0){
				System.out.println("Match Found for JobLocation");
				searchStringLists.addAll(jobPostingDao.findByJobLocation(str));
			}
			
			System.out.println("Searching for job Resp");
			if(jobPostingDao.findByJobResponsibilities(str).size() > 0){
				System.out.println("Match Found for Job Responsibilities");
				searchStringLists.addAll(jobPostingDao.findByJobResponsibilities(str));
			}
			
			System.out.println("Searching for job salary");
			if(jobPostingDao.findByJobSalary(str).size() > 0){
				System.out.println("Match Found for Salary");
				searchStringLists.addAll(jobPostingDao.findByJobSalary(str));
			}
			
			System.out.println("Searching for job status");
			if(jobPostingDao.findByJobStatus(str).size() > 0){
				System.out.println("Match Found for Status");
				searchStringLists.addAll(jobPostingDao.findByJobStatus(str));
			}
			
			System.out.println("Searching for job title");
			if(jobPostingDao.findByJobTitle(str).size() > 0){
				System.out.println("Match Found for Job Title");
				searchStringLists.addAll(jobPostingDao.findByJobTitle(str));
			}
			
			System.out.println("Searching for job company name");
			if(companyDAO.findByNameCompanyName(str).size() > 0){
				System.out.println("Match Found for Company Name");
				List<Company> companies = companyDAO.findByNameCompanyName(str);
				for (Company company : companies){
					searchStringLists.addAll(company.getJobPostingList());
				}
			}
		}
		System.out.println("Service Layer: searchStringLists has size "+searchStringLists.size());	
		HashSet<String> hs = new HashSet<String>(); //String is jobId
		for (int i=0;i<searchStringLists.size();i++){
			if(!hs.contains(searchStringLists.get(i).getJobId())){
				System.out.println("hashset has size(): "+hs.size());
				System.out.println("Adding "+searchStringLists.get(i).getJobId()+" to hashset");			
				hs.add(searchStringLists.get(i).getJobId());
				System.out.println("After adding hashset has size(): "+hs.size());
				System.out.println("Does hs contains "+searchStringLists.get(i).getJobId()+": "+hs.contains(searchStringLists.get(i).getJobId()));
			}
			else{
				System.out.println("After removing "+searchStringLists.get(i).getJobId());
				searchStringLists.remove(i);
				System.out.println("searchStringLists has size "+searchStringLists.size());
			}
		}
		for(int i=0;i<searchStringLists.size();i++){
			if(searchStringLists.get(i).getJobStatus().equals("open")){
				searchStringLists.remove(i);
			}
		}
		System.out.println("open searchStringLists has size "+searchStringLists.size());
		return searchStringLists;
	}	
//	public List<JobPosting> searchBySalaryRange(int min, int max){
//		ArrayList<JobPosting> jobList = jobPostingDao.findBySalaryRange(min,max);
//		return null;
//	}
	public List<JobPosting> searchBySalaryRange(int minimum, int maximum) {
		List<JobPosting> searchSalaryList = new ArrayList<JobPosting>();
		System.out.println("Inside searchBySalaryRange() JobService.java");
		searchSalaryList = jobPostingDao.findBySalaryRange(minimum, maximum);
		System.out.println("searchSalaryList has size "+searchSalaryList.size());
		
		for(int i=0;i<searchSalaryList.size();i++){
			if(!searchSalaryList.get(i).getJobStatus().equals("open")){
				searchSalaryList.remove(i);
			}
		}
		System.out.println("open searchSalaryList has size "+searchSalaryList.size());
		return searchSalaryList;
	}
	public List<JobPosting> searchByFields(String jobId, String title, String location, String salary, String status,
			String postedOn) {
		// TODO Auto-generated method stub
		List<JobPosting> searchList = new ArrayList<JobPosting>();
		
		if(jobId != null && !jobId.isEmpty()){
			System.out.println("jobId is not null---finding");
			searchList.add(jobPostingDao.findByJobId(jobId));
		}
		if(title != null && !title.isEmpty()){
			System.out.println("title is not null---finding");
			searchList.addAll(jobPostingDao.findByJobTitle(title));
		}
		
		if(location != null && !location.isEmpty()){
			System.out.println("location is not null---finding");
			searchList.addAll(jobPostingDao.findByJobLocation(location));
		}
		
		if(salary != null && !salary.isEmpty()){
			System.out.println("salary is not null---finding");
			searchList.addAll(jobPostingDao.findByJobSalary(salary));
		}
		
		if(status != null && !status.isEmpty()){
			System.out.println("status is not null---finding");
			searchList.addAll(jobPostingDao.findByJobStatus(status));
		}
		
		//System.out.println("searchList has size: "+searchList.size());
		System.out.println("Service Layer: searchStringLists has size "+searchList.size());	
		HashSet<String> hs = new HashSet<String>(); //String is jobId
		for (int i=0;i<searchList.size();i++){
			if(!hs.contains(searchList.get(i).getJobId())){
				System.out.println("hashset has size(): "+hs.size());
				System.out.println("Adding "+searchList.get(i).getJobId()+" to hashset");			
				hs.add(searchList.get(i).getJobId());
				System.out.println("After adding hashset has size(): "+hs.size());
				System.out.println("Does hs contains "+searchList.get(i).getJobId()+": "+hs.contains(searchList.get(i).getJobId()));
			}
			else
			{
				System.out.println("Removing "+searchList.get(i).getJobId());
				searchList.remove(i);
				System.out.println("searchStringLists now has size "+searchList.size());
			}
		}
		
		for(int i=0;i<searchList.size();i++){
			if(!searchList.get(i).getJobStatus().equals("open")){
				searchList.remove(i);
			}
		}
		System.out.println("open searchStringLists now has size "+searchList.size());
		return searchList;
		
	}
	public String markApplicationAsInterested(String jobId, String userName) {
		// TODO Auto-generated method stub
		
		JobPosting interestedJob = jobPostingDao.findByJobId(jobId);
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(userName);
		
		for(int i=0;i<jobSeeker.getApplicationsList().size();i++){
			if (jobSeeker.getApplicationsList().get(i).getId().equals(jobId)){
				//JobSeeker has already applied cannot mark it is as interested
				return "You have already applied to this job!";
				
			}
		}
		for(int i=0;i<jobSeeker.getInterestedList().size();i++){
			if(jobSeeker.getInterestedList().get(i).getJobId().equals(jobId)){
				return "This job is already in your interest list";
			}
		}
		
		System.out.println("Interested Job does not exist with jobseeker");
		System.out.println("Adding it to interested Jobs");
		interestedJob.getInterestedApplicants().add(jobSeeker);
		jobPostingDao.save(interestedJob);
		jobSeeker.getInterestedList().add(interestedJob);
		System.out.println("Added to interested Jobs... Saving changes to DB");
		jobSeekerDAO.save(jobSeeker);
		System.out.println("Save Successful !");
		return "Marked as interested";
		
	}
	public String markApplicationAsUninterested(String jobId, String userName) {
		// TODO Auto-generated method stub
		JobPosting interestedJob = jobPostingDao.findByJobId(jobId);
		System.out.println("job ID to be removed"+ jobId);
		JobSeeker jobSeeker = jobSeekerDAO.findByUsername(userName);
		System.out.println("Printing current interested list" + jobSeeker.getInterestedList().size());
		for(int i=0;i<jobSeeker.getInterestedList().size();i++)
		{
			
			System.out.println(jobSeeker.getInterestedList().get(i).getJobId());
		}
		for(int i=0;i<jobSeeker.getInterestedList().size();i++){
			if(jobSeeker.getInterestedList().get(i).getJobId().equals(jobId)){
				System.out.println("Job Id found to delete");
				jobSeeker.getInterestedList().remove(i);
				System.out.println("Job deleted... Saving to DAO");
				jobSeekerDAO.save(jobSeeker);
				
				interestedJob.getInterestedApplicants().remove(jobSeeker);
				jobPostingDao.save(interestedJob);
				
				return "Marked as uninterested";
			}
		}
		return "This job doesn't exist in interested list";
	}
}