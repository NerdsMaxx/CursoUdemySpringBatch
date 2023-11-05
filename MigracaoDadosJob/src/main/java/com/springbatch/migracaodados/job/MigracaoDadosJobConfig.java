package com.springbatch.migracaodados.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@EnableBatchProcessing
@Configuration
public class MigracaoDadosJobConfig {
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Bean
    public Job migracaoDadosJob(
            @Qualifier("migracaoPessoaStep") final Step migracaoPessoaStep,
            @Qualifier("migracaoDadosBancariosStep") final Step migracaoDadosBancariosStep) {
        return jobBuilderFactory
                .get("migracaoDadosJob")
                .start(stepParalelos(migracaoPessoaStep, migracaoDadosBancariosStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }
    
    private Flow stepParalelos(Step migracaoPessoaStep, Step migracaoDadosBancariosStep) {
        final Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migrarDadosBancariosFlow")
                .start(migracaoDadosBancariosStep)
                .build();
        
        return new FlowBuilder<Flow>("stepsParalelosFlow")
                .start(migracaoPessoaStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();
    }
}