package com.springbatch.arquivolargurafixa.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivolargurafixa.dominio.Cliente;

@Configuration
public class LeituraArquivoLarguraFixaWriterConfig {
	@Bean
	public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
		return items -> items.forEach(System.out::println);
//		return clientes -> {
//			for(final Cliente cliente : clientes) {
//				if(cliente.getNome().equals("Maria")) {
//					throw new RuntimeException();
//				}
//
//				System.out.println(cliente);
//			}
//		};
	}
}