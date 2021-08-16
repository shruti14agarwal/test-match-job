package com.test.swipejobs.testmatchjobapi.service;

import java.util.List;

import com.test.swipejobs.testmatchjobapi.generateddto.Job;
import com.test.swipejobs.testmatchjobapi.generateddto.Worker;

public interface MatchingJobService {
	
	/**
	 * returns the 'n' suitable jobs for a worker.
	 * @param worker
	 * @param jobs
	 * @param n
	 * @return
	 */
	public List<Job> getMatchingJobs(Worker worker, List<Job> jobs, Integer n);

}
