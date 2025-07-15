package com.example.jobms.job.clients;

import com.example.jobms.job.external.Review;
import com.example.jobms.job.external.company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="Review")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getAllReviews(@RequestParam("companyId") Long companyId);
}


