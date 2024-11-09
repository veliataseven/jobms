package com.va.jobms.job.service;


import com.va.jobms.job.dto.JobDTO;
import com.va.jobms.job.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JobService {

    List<JobDTO> findAll();

    Optional<JobDTO> findById(Long id);

    Job createJob(Job job);

    Optional<Job> deleteJob(Long id);

    Job updateJob(Long id, Job job);
}
