package com.example.jobms.job;

import com.example.jobms.job.dto.JobwithCompannyDTO;
import com.example.jobms.job.external.company;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
//    public List<JobwithCompannyDTO> findAll() {
//        List<jobs> list = jobRepository.findAll();
//        List<JobwithCompannyDTO> jobwithCompannyDTOs = new ArrayList<>();
//        RestTemplate restTemplate = new RestTemplate();
//        for (jobs job : list) {
//            JobwithCompannyDTO jobwithCompannyDTO = new JobwithCompannyDTO();
//            jobwithCompannyDTO.setJob(job);
//            company Company = restTemplate.getForObject("http://localhost:8083/GetComapines", company.class);
//            jobwithCompannyDTO.setCompany(Company);
//            jobwithCompannyDTOs.add(jobwithCompannyDTO);
//////        //System.out.println(("Company:"+Company.getName()));
//////        assert Company != null;
//////        System.out.println(("Company:"+Company.getCompanyid()));
//////        System.out.println(("Company:"+Company.getName()));
//////        return jobRepository.findAll();
////
////
////        try {
////            ResponseEntity<List<company>> response = restTemplate.exchange(
////                    "http://localhost:8083/GetComapines",
////                    HttpMethod.GET,
////                    null,
////                    new ParameterizedTypeReference<List<company>>() {}
////            );
////            List<company> companies = response.getBody();
////
////            if (companies != null && !companies.isEmpty()) {
////                for (company c : companies) {
////                    System.out.println("Company: " + c.getCompanyid() + ", Name: " + c.getName());
////                }
////            }
////        } catch (Exception e) {
////            System.err.println("Failed to fetch companies: " + e.getMessage());
////            e.printStackTrace(); // Optional: More details
////        }
//
//
//        }
//        return jobwithCompannyDTOs;
//    }
    public List<JobwithCompannyDTO> findAll() {
        RestTemplate restTemplate = new RestTemplate();

        List<company> companies = new ArrayList<>();
        try {
            ResponseEntity<List<company>> response = restTemplate.exchange(
                    "http://localhost:8083/GetComapines",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<company>>() {}
            );
            companies = response.getBody();
        } catch (Exception e) {
            System.err.println("Failed to fetch companies: " + e.getMessage());
            e.printStackTrace();
        }

        List<jobs> jobList = jobRepository.findAll();
        List<JobwithCompannyDTO> result = new ArrayList<>();

        for (jobs job : jobList) {
            JobwithCompannyDTO dto = new JobwithCompannyDTO();
            dto.setJob(job);

            // Match company from external service using job.getCompanyid() if available
            if (companies != null) {
                for (company comp : companies) {
                    if (comp.getCompanyid().equals(job.getCompanyid())) {
                        dto.setCompany(comp);
                        break;
                    }
                }
            }

            result.add(dto);
        }

        return result;
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
