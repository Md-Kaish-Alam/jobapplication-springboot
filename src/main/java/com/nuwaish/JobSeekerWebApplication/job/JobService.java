package com.nuwaish.JobSeekerWebApplication.job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();

    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJobData);
}
