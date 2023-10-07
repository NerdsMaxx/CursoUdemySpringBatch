package com.udemy.aulaspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableBatchProcessing
//@Configuration
public class BatchConfig {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job imprimeOlaJob(Step imprimeOlaJob) {
//        return jobBuilderFactory.get("imprimeOlaJob")
//                                .start(imprimeOlaJob)
//                                .incrementer(new RunIdIncrementer())
//                                .build();
//    }
//
//    public Step imprimeOlaStep() {
//        return stepBuilderFactory.get("imprimeOlaStep")
//                                 .tasklet(imprimeTaskletOla(null))
//                                 .build();
//    }
//
//    @Bean
//    @StepScope
//    public Tasklet imprimeTaskletOla(@Value("#{jobParameters['nome']}") String nome) {
//        return (contribution, context) -> {
//            System.out.printf("Olá, %s!%n", nome);
//            return RepeatStatus.FINISHED;
//        };
//    }
}