package com.example.jobms.job.external;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class company {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long companyid;
        private String name;
        private String description;



        public company() {

        }



        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }





        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }



        public Long getCompanyid() {
            return companyid;
        }

        public void setCompanyid(Long companyid) {
            this.companyid = companyid;
        }
    }


