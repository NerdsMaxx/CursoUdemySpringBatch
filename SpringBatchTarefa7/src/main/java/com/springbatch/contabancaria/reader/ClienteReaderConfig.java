package com.springbatch.contabancaria.reader;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springbatch.contabancaria.dominio.Cliente;

@Configuration
public class ClienteReaderConfig {

	@Bean
	@StepScope
	public JdbcCursorItemReader<Cliente> clienteReader(
			@Qualifier("appDataSource") DataSource datasource) {
		return new JdbcCursorItemReaderBuilder<Cliente>()
				.name("clienteReader")
				.dataSource(datasource)
				.sql("select * from cliente")
				.rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
				.build();
	}

}
