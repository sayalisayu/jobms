package com.example.jobms.job;

import com.example.jobms.job.dto.JobwithCompannyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class jobcontroller {
    @Autowired
    private jobService service;
    public jobcontroller(jobService service){
        this.service=service;
    }
    private List<jobs>jobs=new ArrayList<>();
    @GetMapping("/jobs")
    public ResponseEntity<List<JobwithCompannyDTO>> findAll()
    {

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @PostMapping("/Save")
    public ResponseEntity<String> Createjob(@RequestBody jobs Jobs)
    {
        service.createjob(Jobs);
        return new ResponseEntity<>("Value save successfully ",HttpStatus.OK) ;
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<jobs> findById(@PathVariable Long id)
    {     jobs job=service.getJobByid(id);
        if(job!=null)
        {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("DeleteJob/{id}")
    public ResponseEntity<String> DeleteJob(@PathVariable Long id)
    {
        boolean job=service.delete(id);
        if(job)
        {
           return new ResponseEntity<>("Job deleted ", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("UpdateJobs/{id}")
    public ResponseEntity<String> UpdateJob(@PathVariable Long id,@RequestBody jobs job)
    {
        boolean Updatejobs=service.UpdateJob(id,job);
        if(Updatejobs)
            return new ResponseEntity<>("Job updated Succesfully ",HttpStatus.OK) ;
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //GET /jobs/{id}:Get a specific job by ID
    //POST /jobs:Create a new job(request body should contain the job details)
    //DELETE /jobs/{id}:Delete a specific job by ID
    //PUT /jobs/{id}: Update a specific job by ID(request body should contain the updated job
    //GET /jobs/{id}/company: Get the company associated with a specific job by ID
    //Example API URLs:
    //GET {base_url}/jobs
    //GET {base_url}/jobs/1
    //POST {base_url}/jobs
    //DELETE {base_url}/jobs/1
    //PUT {base_url}/jobs/1
    //
}
