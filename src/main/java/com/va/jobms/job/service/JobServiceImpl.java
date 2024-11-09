package com.va.jobms.job.service;

import com.va.jobms.job.dto.JobDTO;
import com.va.jobms.job.external.Company;
import com.va.jobms.job.external.Review;
import com.va.jobms.job.mapper.JobMapper;
import com.va.jobms.job.model.Job;
import com.va.jobms.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).toList();
    }

    public JobDTO convertToDto(Job job) {
        // Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);
        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(), Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });

        List<Review> reviews = reviewResponse.getBody();
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
        jobDTO.setCompany(company);
        return jobDTO;
    }

    @Override
    public Optional<JobDTO> findById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        JobDTO jobDTO = convertToDto(job.get());

        return Optional.ofNullable(jobDTO);
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
