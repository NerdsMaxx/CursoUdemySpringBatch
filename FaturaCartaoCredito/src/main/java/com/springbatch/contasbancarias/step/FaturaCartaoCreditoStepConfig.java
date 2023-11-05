package com.springbatch.contasbancarias.step;

import com.springbatch.contasbancarias.dominio.FaturaCartaoCredito;
import com.springbatch.contasbancarias.dominio.Transacao;
import com.springbatch.contasbancarias.reader.FaturaCartaoCreditoReader;
import com.springbatch.contasbancarias.writer.TotalTransacoesFooterCallback;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaCartaoCreditoStepConfig {
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step faturaCartaoCreditoStep(
            final ItemStreamReader<Transacao> lerTransacoesReader,
            final ItemProcessor<FaturaCartaoCredito,FaturaCartaoCredito> carregarDadosClienteProcessor,
            final ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito,
            final TotalTransacoesFooterCallback listener) {
        return stepBuilderFactory
                .get("faturaCartaoCreditoStep")
                .<FaturaCartaoCredito,FaturaCartaoCredito>chunk(1)
                .reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
                .processor(carregarDadosClienteProcessor)
                .writer(escreverFaturaCartaoCredito)
                .listener(listener)
                .build();
    }
}