package com.springbatch.contabancaria.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.contabancaria.dominio.Cliente;
import com.springbatch.contabancaria.dominio.ContaBancaria;

@Configuration
public class ClienteContaBancariaStepConfig {
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step clienteContaBancariaStep(
			ItemReader<Cliente> clienteItemReader,
			ItemProcessor<Cliente, ContaBancaria> clienteContaBancariaProcessor,
			ItemWriter<ContaBancaria> contaBancariaWriter) {
		return stepBuilderFactory
				.get("clienteContaBancariaStep")
				.<Cliente, ContaBancaria>chunk(1)
				.reader(clienteItemReader)
				.processor(clienteContaBancariaProcessor)
				.writer(contaBancariaWriter)
				.build();
	}
}
