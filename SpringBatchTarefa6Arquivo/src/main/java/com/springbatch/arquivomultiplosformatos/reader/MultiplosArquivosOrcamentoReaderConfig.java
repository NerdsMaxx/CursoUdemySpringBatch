package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Orcamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultiplosArquivosOrcamentoReaderConfig {
    
    @StepScope
    @Bean
    public MultiResourceItemReader<Orcamento> multiplosArquivosOrcamentoReader(
            @Value("#{jobParameters['arquivosOrcamentos']}") Resource[] arquivosOrcamentos,
            FlatFileItemReader<Orcamento> arquivoOrcamentoItemReader) {
        return new MultiResourceItemReaderBuilder<Orcamento>()
                .name("multiplosArquivosOrcamentoReader")
                .resources(arquivosOrcamentos)
                .delegate(arquivoOrcamentoItemReader)
                .build();
    }
}