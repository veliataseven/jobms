package com.va.jobms.job.service;

import com.va.jobms.job.model.Job;
import com.va.jobms.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> findById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Optional<Job> jobToBeUpdated = jobRepository.findById(id);
        if (jobToBeUpdated.isPresent()) {
            Job updatedJob = jobToBeUpdated.get();
            updatedJob.setTitle(job.getTitle());
            updatedJob.setDescription(job.getDescription());
            updatedJob.setMinSalary(job.getMinSalary());
            updatedJob.setMaxSalary(job.getMaxSalary());
            updatedJob.setLocation(job.getLocation());
            return jobRepository.save(updatedJob);
        } else throw new IllegalArgumentException("Job not found");
    }

    @Override
    public Optional<Job> deleteJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            jobRepository.delete(job.get());
            return job;
        } else throw new IllegalArgumentException("Job not found");
    }
}
