package com.job.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String company_name;

    private String company_addr;

    private String company_info;

    private String job_name;

    private String job_addr;

    private String job_info;

    private Integer salary_min;

    private Integer salary_max;

    private String url;

    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr == null ? null : company_addr.trim();
    }

    public String getCompany_info() {
        return company_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info == null ? null : company_info.trim();
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name == null ? null : job_name.trim();
    }

    public String getJob_addr() {
        return job_addr;
    }

    public void setJob_addr(String job_addr) {
        this.job_addr = job_addr == null ? null : job_addr.trim();
    }

    public String getJob_info() {
        return job_info;
    }

    public void setJob_info(String job_info) {
        this.job_info = job_info == null ? null : job_info.trim();
    }

    public Integer getSalary_min() {
        return salary_min;
    }

    public void setSalary_min(Integer salary_min) {
        this.salary_min = salary_min;
    }

    public Integer getSalary_max() {
        return salary_max;
    }

    public void setSalary_max(Integer salary_max) {
        this.salary_max = salary_max;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    @Override
    public String toString() {
        return "JobInfoDao{" +
                "id=" + id +
                ", company_name='" + company_name + '\'' +
                ", company_addr='" + company_addr + '\'' +
                ", company_info='" + company_info + '\'' +
                ", job_name='" + job_name + '\'' +
                ", job_addr='" + job_addr + '\'' +
                ", job_info='" + job_info + '\'' +
                ", salary_min=" + salary_min +
                ", salary_max=" + salary_max +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}