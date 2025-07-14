package com.example.jobms.job;

import java.util.List;

public interface jobService {
    List<jobs> findAll();
    void createjob(jobs job);

    jobs getJobByid(Long id);

    boolean delete(Long id);

    boolean UpdateJob(Long id,jobs job);
}
