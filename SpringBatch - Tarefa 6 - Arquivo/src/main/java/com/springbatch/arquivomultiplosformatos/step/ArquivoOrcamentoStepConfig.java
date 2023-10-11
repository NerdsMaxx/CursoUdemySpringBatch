package com.springbatch.arquivomultiplosformatos.step;

import com.springbatch.arquivomultiplosformatos.dominio.Orcamento;
import com.springbatch.arquivomultiplosformatos.writer.ArquivoOrcamentoWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoOrcamentoStepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step arquivoOrcamentoStep(
            MultiResourceItemReader<Orcamento> multiplosArquivosOrcamentoReader,
            ArquivoOrcamentoWriter arquivoOrcamentoWriter) {
        return stepBuilderFactory
                .get("arquivoOrcamentoStep")
                .<Orcamento, Orcamento>chunk(50)
                .reader(multiplosArquivosOrcamentoReader)
                .writer(arquivoOrcamentoWriter)
                .build();
    }
}