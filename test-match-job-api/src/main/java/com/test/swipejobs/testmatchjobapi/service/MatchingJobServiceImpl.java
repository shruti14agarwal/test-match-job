package com.test.swipejobs.testmatchjobapi.service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.test.swipejobs.testmatchjobapi.generateddto.Job;
import com.test.swipejobs.testmatchjobapi.generateddto.Worker;
import com.test.swipejobs.testmatchjobapi.util.MatchJobUtil;

@Service
public class MatchingJobServiceImpl implements MatchingJobService {
	
	private HashMap<Integer, Double> jobDistance = new HashMap<>();
	
	/**
	 * {@inheritDoc}
	 */
	public List<Job> getMatchingJobs(Worker worker, List<Job> jobs, Integer n) {
		return jobs.stream().filter(job -> (matchjob(worker, job))).sorted((job1, job2) -> getBestJobs(job1, job2)).limit(n).collect(Collectors.toList());
	}
	
	
	private boolean matchjob(Worker worker, Job job) {
		boolean match = true;
		// checking if driver license is required.
		if (job.getDriverLicenseRequired()) {
			match = worker.getHasDriversLicense();
		} 
		// matching certificates
		if (match && Collections.indexOfSubList(worker.getCertificates(), job.getRequiredCertificates()) != -1) {
			// matching availability of job start date
			String jobStartDay = MatchJobUtil.getDayOfWeek(job.getStartDate());
			if (worker.getAvailability().stream().anyMatch(availabilty -> availabilty.getTitle().equalsIgnoreCase(jobStartDay))) {
				// matching the distance
				double distance = MatchJobUtil.getDistance(Double.parseDouble(worker.getJobSearchAddress().getLatitude())  ,
						Double.parseDouble(worker.getJobSearchAddress().getLongitude()), Double.parseDouble(job.getLocation().getLatitude()),
						Double.parseDouble(job.getLocation().getLongitude()), worker.getJobSearchAddress().getUnit());
				//if workers max distance is more than the calcualted distance
				if (worker.getJobSearchAddress().getMaxJobDistance() < distance) {
					//store this distance somewhere
					match = false;
				} else {
					jobDistance.put(job.getJobId(), distance);
				}
			} else {
				match = false;
			}
		} else {
			match = false;
		}
		
		return match;
	}
	
	
	
	private  int getBestJobs(Job job1, Job job2) {
		int result = 0;
		try {
			Double billrate1 = NumberFormat.getCurrencyInstance(Locale.US).parse(job1.getBillRate()).doubleValue();
			Double billrate2 = NumberFormat.getCurrencyInstance(Locale.US).parse(job2.getBillRate()).doubleValue();
			result = billrate2.compareTo(billrate1);
			if(result == 0) {
				result = jobDistance.get(job1.getJobId()).compareTo(jobDistance.get(job2.getJobId()));
				if(result == 0) {
					result = job2.getWorkersRequired().compareTo(job1.getWorkersRequired());
				}
			} 

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
}
