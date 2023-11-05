package com.springbatch.folhaponto.writer;

import com.springbatch.folhaponto.dominio.FolhaPonto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompositeFolhaPontoWriterConfig {
    
    @Bean
    public CompositeItemWriter<FolhaPonto> compositeFolhaPontoWriter(
            ItemWriter<FolhaPonto> imprimeFolhaPontoWriter,
            JdbcBatchItemWriter<FolhaPonto> folhaPontoJdbcWriter) {
        return new CompositeItemWriterBuilder<FolhaPonto>()
                .delegates(imprimeFolhaPontoWriter, folhaPontoJdbcWriter)
                .build();
    }
}