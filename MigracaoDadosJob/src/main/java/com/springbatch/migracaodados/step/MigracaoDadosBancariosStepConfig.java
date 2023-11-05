package com.springbatch.migracaodados.step;

import com.springbatch.migracaodados.dominio.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracaoDadosBancariosStepConfig {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step migracaoDadosBancariosStep(
            @Qualifier("arquivoDadosBancariosReader") final FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader,
            @Qualifier("bancoDadosBancariosWriter") final ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {
        return stepBuilderFactory
                .get("migracaoDadosBancariosStep")
                .<DadosBancarios, DadosBancarios>chunk(10_000)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }
}