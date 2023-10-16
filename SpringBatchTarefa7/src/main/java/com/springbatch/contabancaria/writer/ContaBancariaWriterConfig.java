package com.springbatch.contabancaria.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.contabancaria.dominio.ContaBancaria;

@Configuration
public class ContaBancariaWriterConfig {

	@Bean
	public ItemWriter<ContaBancaria> contaBancariaWriter() {
		return items -> items.forEach(System.out::println);
	}
}
