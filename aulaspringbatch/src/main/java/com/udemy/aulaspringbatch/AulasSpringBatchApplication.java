package com.udemy.aulaspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AulasSpringBatchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AulasSpringBatchApplication.class, args);
	}

	@RestController
	public class Testar {
		@Autowired
		private JobLauncher jobLauncher;
		
		@Autowired
		private Job imprimeParImparJob;
		
		@Autowired
		private Job imprimeOlaJob;
		
		@GetMapping("/testar")
		public void testar() {
			try {
				final JobParameters jobParameters = new JobParametersBuilder()
						.addLong("time", System.currentTimeMillis())
						.toJobParameters();
				
				jobLauncher.run(imprimeOlaJob, jobParameters);
				jobLauncher.run(imprimeParImparJob, jobParameters);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			
		}
	}
}