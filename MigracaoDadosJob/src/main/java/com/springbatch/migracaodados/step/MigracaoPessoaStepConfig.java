package com.springbatch.migracaodados.step;

import com.springbatch.migracaodados.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracaoPessoaStepConfig {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step migracaoPessoaStep(
            @Qualifier("arquivoPessoaReader") final FlatFileItemReader<Pessoa> arquivoPessoaReader,
            @Qualifier("pessoaClassifierWriter") final ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
            @Qualifier("arquivoPessoaInvalidaWriter") final FlatFileItemWriter<Pessoa> arquivoPessoaInvalidaWriter) {
        return stepBuilderFactory
                .get("migracaoPessoaStep")
                .<Pessoa, Pessoa>chunk(10_000)
                .reader(arquivoPessoaReader)
                .writer(pessoaClassifierWriter)
                .stream(arquivoPessoaInvalidaWriter)
                .build();
    }
}