package com.example.jobms.job;

import com.example.jobms.job.dto.JobwithCompannyDTO;

import java.util.List;

public interface jobService {
    List<JobwithCompannyDTO> findAll();
    void createjob(jobs job);

    JobwithCompannyDTO getJobByid(Long id);

    boolean delete(Long id);

    boolean UpdateJob(Long id,jobs job);
}
