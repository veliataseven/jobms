package com.va.jobms.job.controller;

import com.va.jobms.job.dto.JobWithCompanyDTO;
import com.va.jobms.job.model.Job;
import com.va.jobms.job.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobWithCompanyDTO>> getJobs() {
        List<JobWithCompanyDTO> jobs = jobService.findAll();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        Optional<Job> job = jobService.findById(id);
        return job.map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("Job not found"));
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job jobCreated = jobService.createJob(job);
        return ResponseEntity.ok(jobCreated);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
        Job jobUpdated = jobService.updateJob(id, job);
        return ResponseEntity.ok(jobUpdated);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable Long id) {
        Optional<Job> jobDeleted = jobService.deleteJob(id);
        return jobDeleted.map(ResponseEntity::ok).orElseThrow(() -> new IllegalArgumentException("Job not found"));
    }
}
