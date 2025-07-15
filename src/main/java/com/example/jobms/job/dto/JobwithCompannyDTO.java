package com.example.jobms.job.dto;

import com.example.jobms.job.external.Review;
import com.example.jobms.job.external.company;
import com.example.jobms.job.jobs;

import java.util.List;

public class JobwithCompannyDTO {
    private Long companyid;
    private String title;
    private String description;
    private String minsalary;
    private String maxsalary;
    private String location;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    private company Company;
    private List<Review> reviews;

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinsalary(String minsalary) {
        this.minsalary = minsalary;
    }

    public void setMaxsalary(String maxsalary) {
        this.maxsalary = maxsalary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompany(company company) {
        Company = company;
    }

    public Long getCompanyid() {
        return companyid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMinsalary() {
        return minsalary;
    }

    public String getMaxsalary() {
        return maxsalary;
    }

    public String getLocation() {
        return location;
    }

    public company getCompany() {
        return Company;
    }
}
