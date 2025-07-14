package com.example.jobms.job;



import jakarta.persistence.*;

@Entity
//@Table(name="Job_table")
public class jobs {
      @Id
      @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    private String title;
    private String description;
    private String minsalary;
    private String maxsalary;
    private String location;
    private Long companyid;

    public Long getCompanyid() {
        return companyid;
    }

    public jobs(Long id, String title, String description, String minsalary, String maxsalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minsalary = minsalary;
        this.maxsalary = maxsalary;
        this.location = location;
    }

    public jobs() {

    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
}
