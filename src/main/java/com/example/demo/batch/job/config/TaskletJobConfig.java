package com.example.demo.batch.job.config;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.batch.job.service.BusinessTasklet;

@Slf4j
//@EnableBatchProcessing
//@Configuration
//@Component
public class TaskletJobConfig {

	@Autowired 
	public JobBuilderFactory jobBuilderFactory;
    
	@Autowired 
	public StepBuilderFactory stepBuilderFactory;
    
    private Scheduler scheduler;
    
    /*@Bean
    public Job TaskletJob(){

        Job customJob = jobBuilderFactory.get("taskletJob")
                .start(TaskStep())
                .build();

        return customJob;
    }

    @Bean
    public Step TaskStep(){
        return stepBuilderFactory.get("taskletStep")
                .tasklet((contribution, chunkContext) ->{

                    //비즈니스 로직
                    for(int idx = 0; idx < 10; idx ++){
                        log.info("TaskStep >> [idx] = " + idx);
                    }

                   return RepeatStatus.FINISHED;
                }).build();
    }*/
    
    @Bean
    public Job TaskletJob(){
    	
        Job customJob = jobBuilderFactory.get("taskletJob")
                .start(TaskStep())
                .build();
        
        /*
        CronTrigger cronTrigger;      // Trigger 객체
        cronTrigger = TriggerBuilder.newTrigger().withIdentity("taskletJob")
        				.withSchedule(CronScheduleBuilder.cronSchedule("/10 * * * * ?").withMisfireHandlingInstructionDoNothing())
        				.forJob("taskletJob").build();
        
        try {
        	scheduler.scheduleJob(cronTrigger);
			
		} catch (SchedulerException e) {
            log.error("ex while registering schedule {}", e.getMessage());
        } catch (Exception e) {
            log.error("ex while registering schedule {}", e.getMessage());
        }*/

        return customJob;
    }

    @Bean
    public Step TaskStep(){
    	log.info("TaskStep");
        return stepBuilderFactory.get("taskletStep")
                .tasklet(new BusinessTasklet())
                .build();
    }
}
