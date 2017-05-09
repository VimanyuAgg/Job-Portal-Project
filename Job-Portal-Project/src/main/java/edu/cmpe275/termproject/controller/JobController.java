package edu.cmpe275.termproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmpe275.termproject.model.JobPosting;

public class JobController {

	@RequestMapping("/addjob/{number}")
	public ResponseEntity<?> addJob(@PathVariable int number, @RequestParam String title,@RequestParam String description,@RequestParam String responsibilites, @RequestParam String offliceLocation, @RequestParam String salary ){
	//	JobPosting job=new JobPosting(number, title, title, description, responsibilites, offliceLocation, salary);
		return null;
	}
}
