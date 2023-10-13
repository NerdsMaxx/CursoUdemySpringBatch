package com.springbatch.jdbccursorreader.reader;

import com.springbatch.jdbccursorreader.dominio.Orcamento;
import com.springbatch.jdbccursorreader.mapper.OrcamentoMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcOrcamentoReaderConfig {
    
    @Bean
    public JdbcCursorItemReader<Orcamento> jdbcOrcamentoReader(
            @Qualifier("appDataSource") DataSource datasource,
            OrcamentoMapper orcamentoMapper) {
        return new JdbcCursorItemReaderBuilder<Orcamento>()
                .name("jdbcOrcamentoReader")
                .dataSource(datasource)
                .sql("select * from lancamento")
                .rowMapper(orcamentoMapper)
                .build();
    }
}