package com.springbatch.contasbancarias.writer;

import com.springbatch.contasbancarias.dominio.Conta;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Objects.isNull;

@Configuration
public class ClassifierContaWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Conta> classifierContaWriter(
            FlatFileItemWriter<Conta> clienteInvalidoWriter,
            CompositeItemWriter<Conta> compositeContaWriter) {
        return new ClassifierCompositeItemWriterBuilder<Conta>()
                .classifier(classifier(clienteInvalidoWriter, compositeContaWriter))
                .build();
    }
    
    private Classifier<Conta,ItemWriter<? super Conta>> classifier(FlatFileItemWriter<Conta> clienteInvalidoWriter, CompositeItemWriter<Conta> compositeContaWriter) {
        return conta -> (isNull(conta.getTipo())) ?
                        clienteInvalidoWriter :
                        compositeContaWriter;
    }
}