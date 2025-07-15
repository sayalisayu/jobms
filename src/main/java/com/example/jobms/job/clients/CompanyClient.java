package com.example.jobms.job.clients;

import com.example.jobms.job.external.company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Companyms")
public interface CompanyClient {
@GetMapping("{id}")
     company getCompany(@PathVariable ("id") Long id);
}
