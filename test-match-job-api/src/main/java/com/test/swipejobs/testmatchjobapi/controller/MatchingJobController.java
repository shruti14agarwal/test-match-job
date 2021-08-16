package com.test.swipejobs.testmatchjobapi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.test.swipejobs.testmatchjobapi.generateddto.Job;
import com.test.swipejobs.testmatchjobapi.generateddto.Worker;
import com.test.swipejobs.testmatchjobapi.service.MatchingJobService;

@RestController
@RequestMapping("/api/v1")
public class MatchingJobController {
	
	private static final String JOBS_REST_API = "https://test.swipejobs.com/api/jobs";
	private static final String WORKERS_REST_API = "https://test.swipejobs.com/api/workers";
	
	@Autowired
	private MatchingJobService matchingJobService;
	
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * Gets users by id.
	 *
	 * @param userId
	 *            the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException
	 *             the resource not found exception
	 */
	@GetMapping("/matchingjobs/{id}")
	public ResponseEntity<List<Job>> getUsersById(@PathVariable(value = "id") Integer workerId) {
		Worker worker = getWorker(workerId);
		if (worker == null ) {
			return ResponseEntity.notFound().build();
		} else {
			List<Job> jobs = matchingJobService.getMatchingJobs(worker, getJobs(), 3);
			return ResponseEntity.ok().body(jobs);
		}
	}

	private Worker getWorker(Integer workerId) {
		Worker[] workers = restTemplate.getForObject(WORKERS_REST_API, Worker[].class);
		return Arrays.asList(workers).stream().filter(worker1 -> worker1.getUserId() == workerId).findFirst().orElse(null);
	}
	
	private List<Job> getJobs() {
		Job[] jobs = restTemplate.getForObject(JOBS_REST_API, Job[].class);
		return  Arrays.stream(jobs).collect(Collectors.toList());
	}

}
