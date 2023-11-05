package com.springbatch.folhaponto.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.folhaponto.dominio.Funcionario;

@Configuration
public class FuncionarioReaderJdbcConfig {
	@Bean
	public JdbcCursorItemReader<Funcionario> funcionarioReaderJdbc(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Funcionario>()
				.name("funcionarioReaderJdbc")
				.dataSource(dataSource)
				.sql("select matricula, nome, idade, data, date_part('month', data::timestamp) as mes, date_part('year', data::timestamp) as ano " +
					 "from funcionario left join registro_ponto on matricula = funcionario_id " +
					 "where data is null " +
					 "or (date_part('month', data::timestamp)=date_part('month', now()::timestamp) " +
					 "and date_part('year', data::timestamp)=date_part('year', now()::timestamp)) " +
					 "order by matricula, data")
				.beanRowMapper(Funcionario.class)
				.build();
	}
}