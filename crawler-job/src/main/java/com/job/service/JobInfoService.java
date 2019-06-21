package com.job.service;

import com.job.pojo.JobInfo;

import java.util.List;

public interface JobInfoService {

    //保存工作信息
    public void save(JobInfo jobInfo);

    public List<JobInfo> findJobInfo(JobInfo jobInfo);

}
