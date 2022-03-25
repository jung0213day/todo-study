package com.example.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
//@EnableBatchProcessing
//@Configuration
public class QuartzMainTest {
	
	@Bean
    public static void QuartzMainTest() throws SchedulerException {

		log.info("QuartzMainTest ???? ");
		
		//JobDataMap은 Quartz에서 실행되는 Job에 Key-value 형식으로 값을 전달할수 있다.
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "HELLO");
		
		JobDetail jobDetail = JobBuilder.newJob(QuartzJobTest.class).usingJobData(jobDataMap).build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(
						//CronScheduleBuilder.cronSchedule("/10 * * * * ?")
						CronScheduleBuilder.cronSchedule("0 0 /10 * * ?")
						.withMisfireHandlingInstructionDoNothing())
				.forJob(jobDetail).build();
		
		// 스케줄러 실행 및 JobDetail과 Trigger 정보로 스케줄링
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
        
    }

}
