package com.va.jobms.job.dto;


import com.va.jobms.job.external.Company;
import com.va.jobms.job.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
