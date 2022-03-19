package com.example.demo.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuartzJobTest implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("## Test Job Call!!");
		String name = context.getJobDetail().getJobDataMap().get("jobName").toString();
		log.info("## Job Name : "+name);
		log.info("## Test Job End##");
		
	}

}
