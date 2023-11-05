package com.springbatch.migracaodados.writer;

import com.springbatch.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoPessoaInvalidaWriterConfig {
    
    @Bean
    public FlatFileItemWriter<Pessoa> arquivoPessoaInvalidaWriter() {
        return new FlatFileItemWriterBuilder<Pessoa>()
                .name("arquivoPessoaInvalidaWriter")
                .resource(new FileSystemResource("files/pessoas_invalidas.csv"))
                .delimited()
                .names("id")
                .build();
    }
}