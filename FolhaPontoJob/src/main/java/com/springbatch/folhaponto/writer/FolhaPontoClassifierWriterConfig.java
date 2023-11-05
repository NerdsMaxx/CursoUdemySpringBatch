package com.springbatch.folhaponto.writer;

import com.springbatch.folhaponto.dominio.FolhaPonto;
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
public class FolhaPontoClassifierWriterConfig {
    
    @Bean
    public ClassifierCompositeItemWriter<FolhaPonto> classifierFolhaPontoWriter(
            final CompositeItemWriter<FolhaPonto> compositeFolhaPontoWriter,
            final FlatFileItemWriter<FolhaPonto> folhaPontoInvalidoWriter) {
        return new ClassifierCompositeItemWriterBuilder<FolhaPonto>()
                .classifier(classifier(compositeFolhaPontoWriter, folhaPontoInvalidoWriter)).build();
    }
    
    private Classifier<FolhaPonto,ItemWriter<? super FolhaPonto>> classifier(
            final CompositeItemWriter<FolhaPonto> compositeFolhaPontoWriter,
            final FlatFileItemWriter<FolhaPonto> folhaPontoInvalidoWriter) {
        return (FolhaPonto folhaPonto) -> {
            if(folhaPonto.getRegistrosPontos().isEmpty()) {
                return folhaPontoInvalidoWriter;
            }
            else {
                return compositeFolhaPontoWriter;
            }
        };
    }
}