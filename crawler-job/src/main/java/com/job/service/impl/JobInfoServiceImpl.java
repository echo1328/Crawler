package com.job.service.impl;

import com.job.dao.JobInfoDao;
import com.job.pojo.JobInfo;
import com.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: crawler
 * @description: JobInfoServiceImpl
 * @author: wenlongzhou
 * @create: 2019-06-20 19:32
 **/

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {

        //根据url和发布时间查询数据
        JobInfo params = new JobInfo();
        params.setUrl(jobInfo.getUrl());
        params.setTime(jobInfo.getTime());

        //执行查询
        List<JobInfo> list = this.findJobInfo(params);

        if (list.size() == 0) {
            this.jobInfoDao.saveAndFlush(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {

        //设置查询条件
        Example example = Example.of(jobInfo);

        //执行查询
        List<JobInfo> list = this.jobInfoDao.findAll(example);

        return list;
    }

}
