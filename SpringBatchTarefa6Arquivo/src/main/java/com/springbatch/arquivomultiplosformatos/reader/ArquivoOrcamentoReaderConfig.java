package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Orcamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoOrcamentoReaderConfig {

	@StepScope
	@Bean
	public FlatFileItemReader<Orcamento> arquivoOrcamentoItemReader(
			@Value("#{jobParameters['arquivoOrcamentos']}") Resource arquivoOrcamentos) {
		return new FlatFileItemReaderBuilder<Orcamento>()
				.name("arquivoOrcamentoItemReader")
				.resource(arquivoOrcamentos)
				.delimited()
				.names("codigo", "despesa", "item", "data", "valor")
				.targetType(Orcamento.class)
				.build();
	}

}