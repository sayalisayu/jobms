package com.example.jobms.job;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpljobService implements jobService {
    //private List<jobs> jobs = new ArrayList<>();
    private JobRepository jobRepository;
    private Long NextId = 1L;

    public ImpljobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<jobs> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createjob(jobs job) {

        //job.setId(NextId++);
       jobRepository.save(job);


    }

    @Override
    public jobs getJobByid(Long id) {
//        for (jobs job : jobs) {
//            if (job.getId().equals(id)) {
//                return job;
//            }
//        }
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
//        Iterator<jobs> iterator = jobs.iterator();
//        while (iterator.hasNext()) {
//            jobs job = iterator.next();
//            if (job.getId().equals(id)) {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public boolean UpdateJob(Long id, jobs job) {
//        for (jobs job1 : jobs) {
//            if (job1.getId().equals(id)) {
//
//                job1.setTitle(job.getTitle());
//                job1.setDescription(job.getDescription());
//                job1.setMinsalary(job.getMaxsalary());
//                job1.setMaxsalary(job.getMinsalary());
//                job1.setLocation(job.getLocation());
//                return true;
//            }
//        }
//        return false;
//    }
        Optional<jobs> jobs1 = jobRepository.findById(id);
        if (jobs1.isPresent()) {
//
            jobs jobs2 = jobs1.get();
            jobs2.setTitle(job.getTitle());
            jobs2.setDescription(job.getDescription());
            jobs2.setMinsalary(job.getMaxsalary());
            jobs2.setMaxsalary(job.getMinsalary());
            jobs2.setLocation(job.getLocation());
             jobRepository.save(jobs2);
            return true;
        }
        return false;
    }
}
