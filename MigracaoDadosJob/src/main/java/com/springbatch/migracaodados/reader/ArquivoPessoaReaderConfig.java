package com.springbatch.migracaodados.reader;

import com.springbatch.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

@Configuration
public class ArquivoPessoaReaderConfig {
    
    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }
    
    private FieldSetMapper<Pessoa> fieldSetMapper() {
        return (final FieldSet fieldSet) ->
                Pessoa.builder()
                      .nome(fieldSet.readString("nome"))
                      .email(fieldSet.readString("email"))
                      .dataNascimento(new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()))
                      .idade(fieldSet.readInt("idade"))
                      .id(fieldSet.readInt("id"))
                      .build();
    }
}