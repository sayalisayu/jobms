package com.example.jobms.job;

import com.example.jobms.job.Mapper.JobMapper;
import com.example.jobms.job.clients.CompanyClient;
import com.example.jobms.job.clients.ReviewClient;
import com.example.jobms.job.dto.JobwithCompannyDTO;
import com.example.jobms.job.external.Review;
import com.example.jobms.job.external.company;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CompanyClient CompanyClient;
    //private List<jobs> jobs = new ArrayList<>();
    @Autowired
    private ReviewClient  reviewClient;
    @Autowired
    RestTemplate restTemplate;
    private JobRepository jobRepository;
    private Long NextId = 1L;
    private JobwithCompannyDTO convertDTO (jobs job){
//        JobwithCompannyDTO dto = new JobwithCompannyDTO();
//
//        dto.setJob(job);

        // Assuming job has a companyId field
        company Company=CompanyClient.getCompany(job.getCompanyid());
//        company Company = restTemplate.getForObject(
//                "http://COMPANYMS:8083/GetComapines/" + job.getCompanyid(),
//                company.class
//        );
        List<Review> reviews= reviewClient.getAllReviews(job.getCompanyid());
//      ResponseEntity<List<Review>> reviewResponse=  restTemplate.exchange("http://reviewms:8084/reviews?companyId= "+job.getCompanyid(),HttpMethod.GET,null,new ParameterizedTypeReference<List<Review>>(){});
//      List<Review> reviews=reviewResponse.getBody();
        JobwithCompannyDTO jobwithCompannyDTO = JobMapper.mapToJobWithCompanyDtO(job, Company,reviews);
       // jobwithCompannyDTO.setCompany(Company);

       return jobwithCompannyDTO;
    }
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
        //RestTemplate restTemplate = new RestTemplate();


//            List<JobwithCompannyDTO> result = new ArrayList<>();
//
//            try {
//                // 1. Fetch all jobs from DB
//                List<jobs> jobList = jobRepository.findAll();
//
//                // 2. Fetch all companies from external COMPANYMS
//                ResponseEntity<List<company>> response = restTemplate.exchange(
//                        "http://COMPANYMS:8083/GetComapines",
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<List<company>>() {
//                        }
//                );
//                List<company> companies = response.getBody();
//
//                // 3. Iterate jobs
//                for (jobs job : jobList) {
//                    // 3a. Find matching company
//                    company matchedCompany = null;
//                    if (companies != null) {
//                        for (company comp : companies) {
//                            if (comp.getCompanyid().equals(job.getCompanyid())) {
//                                matchedCompany = comp;
//                                break;
//                            }
//                        }
//                    }
//
//                    // 3b. Fetch reviews for the current job’s companyId
//                    List<Review> reviews = new ArrayList<>();
//                    try {
//                        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
//                                "http://reviewms:8084/reviews?companyId=" + job.getCompanyid(),
//                                HttpMethod.GET,
//                                null,
//                                new ParameterizedTypeReference<List<Review>>() {
//                                }
//                        );
//                        reviews = reviewResponse.getBody();
//                    } catch (Exception e) {
//                        System.err.println("Failed to fetch reviews for companyId " + job.getCompanyid());
//                        e.printStackTrace();
//                    }
//
//                    // 3c. Map to DTO
//                    JobwithCompannyDTO dto = JobMapper.mapToJobWithCompanyDtO(job, matchedCompany, reviews);
//                    result.add(dto);
//                }
//
//            } catch (Exception e) {
//                System.err.println("Failed to fetch companies or jobs: " + e.getMessage());
//                e.printStackTrace();
//            }
//
//            return result;

            List<JobwithCompannyDTO> result = new ArrayList<>();

            try {
                // 1. Fetch all jobs from DB
                List<jobs> jobList = jobRepository.findAll();

                // 2. Iterate jobs
                for (jobs job : jobList) {
                    // 2a. Get company from CompanyClient
                    company company = null;
                    try {
                        company = CompanyClient.getCompany(job.getCompanyid());
                    } catch (Exception e) {
                        System.err.println("Failed to fetch company for companyId " + job.getCompanyid());
                        e.printStackTrace();
                    }

                    // 2b. Get reviews from reviewClient
                    List<Review> reviews = new ArrayList<>();
                    try {
                        reviews = reviewClient.getAllReviews(job.getCompanyid());
                    } catch (Exception e) {
                        System.err.println("Failed to fetch reviews for companyId " + job.getCompanyid());
                        e.printStackTrace();
                    }

                    // 2c. Map to DTO and add to result
                    JobwithCompannyDTO dto = JobMapper.mapToJobWithCompanyDtO(job, company, reviews);
                    result.add(dto);
                }

            } catch (Exception e) {
                System.err.println("Failed to fetch job list: " + e.getMessage());
                e.printStackTrace();
            }

            return result;
        }



        @Override
    public void createjob(jobs job) {

        //job.setId(NextId++);
        jobRepository.save(job);



        // Convert to DTO and return



        // Private helper method to convert to DTO



    }

    @Override
    public JobwithCompannyDTO getJobByid(Long id) {
//        for (jobs job : jobs) {
////            if (job.getId().equals(id)) {
////                return job;
////            }
////        }
//        private JobwithCompannyDTO Convert(jobs Jobs)
//        {
//            JobwithCompannyDTO dto = new JobwithCompannyDTO();
//            dto.setJob(job);
//            company Company = restTemplate.getForObject("http://COMPANYMS:8083/GetComapines/", company.class);
//            dto.setCompany(Company);
//            return dto;
//        }
//        jobs job=jobRepository.findById(id).orElse(null);

//            jobs job = jobRepository.findById(id).orElse(null);
//            if (job == null) {
//                return null; // or throw an exception
//            }
//
//            JobwithCompannyDTO dto = JobMapper.mapToJobWithCompanyDtO(job, Company);
//            dto.setJob(job);
//
//            // Assuming 'job' has a companyId or similar field
//            company Company = restTemplate.getForObject(
//                    "http://COMPANYMS:8083/GetComapines/" + job.getCompanyid(),
//                    company.class
//            );
//            dto.setCompany(Company);
//
//            return dto;
        jobs job = jobRepository.findById(id).orElse(null);
return convertDTO(job);
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
