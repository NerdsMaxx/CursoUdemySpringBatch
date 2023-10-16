package com.springbatch.contabancaria.processor;

import java.util.Objects;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.springbatch.contabancaria.dominio.Cliente;
import com.springbatch.contabancaria.dominio.ContaBancaria;
import com.springbatch.contabancaria.enums.TipoConta;

@Configuration
public class ClienteContaBancariaProcessorConfig {
	
	private int id = 1;

	@Bean
	public ItemProcessor<Cliente, ContaBancaria> clienteContaBancariaProcessor() throws Exception {
		return new FunctionItemProcessor<Cliente, ContaBancaria>(cliente -> {
			final double salario = Objects.requireNonNull(cliente.getFaixaSalarial());
			final TipoConta tipoConta = TipoConta.getTipoConta(salario);

			final ContaBancaria contaBancaria = new ContaBancaria();
			contaBancaria.setId(id++);
			contaBancaria.setClienteId(cliente.getEmail());
			contaBancaria.setTipoConta(tipoConta);

			return contaBancaria;
		});
	}

}
