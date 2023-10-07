package com.udemy.aulaspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.stream.IntStream;

@Configuration
@EnableBatchProcessing
public class ParImparBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Job imprimeParImparJob() {
        return jobBuilderFactory.get("imprimeParImparJob")
                                .start(imprimeParImparStep())
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
    
    public Step imprimeParImparStep() {
        return stepBuilderFactory.get("imprimeParImparStep")
                                 .<Integer,String>chunk(1)
                                 .reader(contaAteDezReader())
                                 .processor(parOuImparProcessor())
                                 .writer(imprimeWriter())
                                 .build();
    }
    
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ItemReader<Integer> contaAteDezReader() {
        return new IteratorItemReader<>(IntStream.range(1, 11).iterator());
    }
    
    public ItemProcessor<Integer,String> parOuImparProcessor() {
        return new FunctionItemProcessor<>(item -> (item % 2 == 0) ? item + " É PAR" : item + " É IMPAR");
    }
    
    public ItemWriter<String> imprimeWriter() {
        return items -> items.forEach(System.out::println);
    }
}