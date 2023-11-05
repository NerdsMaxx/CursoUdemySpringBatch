package com.springbatch.folhaponto.writer;

import com.springbatch.folhaponto.dominio.FolhaPonto;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FolhaPontoInvalidoWriterConfig {

    @Bean
    public FlatFileItemWriter<FolhaPonto> folhaPontoInvalidoWriter() {
        return new FlatFileItemWriterBuilder<FolhaPonto>()
                .name("folhaPontoInvalidoWriter")
                .resource(new FileSystemResource("./files/folhaPontoInvalido.txt"))
                .delimited()
                .names("matricula")
                .build();
    }
}