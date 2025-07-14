package com.example.jobms.job.dto;

import com.example.jobms.job.external.company;
import com.example.jobms.job.jobs;

public class JobwithCompannyDTO {
    private jobs Job;
    private company Company;

    public jobs getJob() {
        return Job;
    }

    public company getCompany() {
        return Company;
    }

    public void setJob(jobs job) {
        Job = job;
    }

    public void setCompany(company company) {
        Company = company;
    }
}
