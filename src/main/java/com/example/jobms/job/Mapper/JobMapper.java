package com.example.jobms.job.Mapper;

import com.example.jobms.job.dto.JobwithCompannyDTO;
import com.example.jobms.job.external.Review;
import com.example.jobms.job.external.company;
import com.example.jobms.job.jobs;

import java.util.List;

public class JobMapper {
    public static JobwithCompannyDTO mapToJobWithCompanyDtO(jobs job, company Company, List<Review> reviews) {
        JobwithCompannyDTO dto = new JobwithCompannyDTO();
        dto.setCompanyid(job.getCompanyid());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setMinsalary(job.getMinsalary());
        dto.setMaxsalary(job.getMaxsalary());
        dto.setLocation(job.getLocation());
        dto.setCompany(Company);
        dto.setReviews(reviews);
        return dto;

    }
}
