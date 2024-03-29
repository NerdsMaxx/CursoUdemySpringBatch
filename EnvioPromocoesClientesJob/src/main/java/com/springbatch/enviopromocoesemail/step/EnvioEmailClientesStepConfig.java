package com.springbatch.enviopromocoesemail.step;

import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EnvioEmailClientesStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Bean
    public Step enviaEmailClientesStep(
            final ItemReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader,
            final ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> processarEmailProdutoClienteProcessor,
            final ItemWriter<SimpleMailMessage> enviarEmailProdutoClienteWriter) {
        return stepBuilderFactory
                .get("enviaEmailClientesStep")
                .<InteresseProdutoCliente, SimpleMailMessage>chunk(1)
                .reader(lerInteresseProdutoClienteReader)
                .processor(processarEmailProdutoClienteProcessor)
                .writer(enviarEmailProdutoClienteWriter)
                .build();
    }
}