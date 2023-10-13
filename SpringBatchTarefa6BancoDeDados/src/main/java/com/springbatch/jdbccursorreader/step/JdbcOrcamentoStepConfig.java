package com.springbatch.jdbccursorreader.step;

import com.springbatch.jdbccursorreader.dominio.Orcamento;
import com.springbatch.jdbccursorreader.writer.JdbcOrcamentoWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcOrcamentoStepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step jdbcOrcamentoStep(ItemReader<Orcamento> jdbcOrcamentoReader,
                                  JdbcOrcamentoWriter jdbcOrcamentoWriter) {
        return stepBuilderFactory
                .get("jdbcOrcamentoStep")
                .<Orcamento,Orcamento>chunk(100)
                .reader(jdbcOrcamentoReader)
                .writer(jdbcOrcamentoWriter)
                .build();
    }
}