package com.batch.core;

import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

//@SpringBootConfiguration
//@EnableBatchProcessing
//@Configuration
//@RestController
public class App {
	
	public static void main(String[] args) {
		
		App a =new App();
		try {
			a.testJOb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	// SpringApplication.run(App.class, args);
}
	
	@RequestMapping("/ShelterJOB")
	public String handle() throws Exception {
 
		
		try {
			String[] springConfig  = 
				{	
						"spring/batch/jobs/job-config.xml" 
				};
		         JobParameters jobParameters=null;
		        
		        
		             // get next long value 
		             long value = (long)(Math.random() * 1000000);
		         JobParametersBuilder jobBuilder= new JobParametersBuilder();
		          jobBuilder.addString("status", "1");
		          jobBuilder.addLong("run.id", value).toJobParameters();
		          jobParameters =jobBuilder.toJobParameters();

		        System.out.println("jobParameters:::"+jobParameters);
		  	
			ApplicationContext context = 
					new ClassPathXmlApplicationContext(springConfig);
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			
	    	
	    	Job job = (Job) context.getBean("ShelterJOB");

	    	JobExecution jobExecution = jobLauncher.run(job, jobParameters);
	        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
		} catch (Exception e) {
			
		}
 
		return "Done";
	
	
}
	

public void testJOb() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		String[] springConfig  = 
			{	
					"META-INF/spring/batch/jobs/job-config.xml" 
			};
	         JobParameters jobParameters=null;
	        
	        
	             // get next long value 
	             long value = (long)(Math.random() * 1000000);
	         JobParametersBuilder jobBuilder= new JobParametersBuilder();
	          jobBuilder.addString("status", "1");
	          jobBuilder.addLong("run.id", value).toJobParameters();
	          jobParameters =jobBuilder.toJobParameters();

	        System.out.println("jobParameters:::"+jobParameters);
	  	
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
    	
    	Job job = (Job) context.getBean("ShelterJOB");

    	JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
        System.out.println("*************************JOB Summary*****************************************");
        System.out.println("Job Name : " + jobExecution.getJobInstance().getJobName());
		System.out.println("Job Status : " + jobExecution.getStatus());
		System.out.println("Job Start Time : " + jobExecution.getStartTime());
		System.out.println("Job End Time : " + jobExecution.getEndTime());
		System.out.println("Job getStepExecutions : " + jobExecution.getStepExecutions().toString());
		//System.out.println("jobExecution:::"+jobExecution.getExitStatus().getExitDescription());
	    System.out.println("*************************End JOB Summary*****************************************");
    	
}
    	

	
	
	
}
