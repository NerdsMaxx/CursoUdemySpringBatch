package com.springbatch.migracaodados.writer;

import com.springbatch.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaClassifierWriterConfig {
    
    @Bean
    public ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter(
            @Qualifier("bancoPessoaWriter") final JdbcBatchItemWriter<Pessoa> bancoPessoaWriter,
            @Qualifier("arquivoPessoaInvalidaWriter") final FlatFileItemWriter<Pessoa> arquivoPessoaInvalidaWriter) {
        return new ClassifierCompositeItemWriterBuilder<Pessoa>()
                .classifier(classifier(bancoPessoaWriter, arquivoPessoaInvalidaWriter))
                .build();
    }
    
    private Classifier<Pessoa,ItemWriter<? super Pessoa>> classifier(JdbcBatchItemWriter<Pessoa> bancoPessoaWriter, FlatFileItemWriter<Pessoa> arquivoPessoaInvalidaWriter) {
        return (final Pessoa pessoa) ->
                (pessoa.isValida()) ?
                bancoPessoaWriter :
                arquivoPessoaInvalidaWriter;
    }
}