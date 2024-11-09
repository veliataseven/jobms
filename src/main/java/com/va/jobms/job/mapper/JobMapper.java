package com.va.jobms.job.mapper;

import com.va.jobms.job.dto.JobDTO;
import com.va.jobms.job.external.Company;
import com.va.jobms.job.external.Review;
import com.va.jobms.job.model.Job;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
