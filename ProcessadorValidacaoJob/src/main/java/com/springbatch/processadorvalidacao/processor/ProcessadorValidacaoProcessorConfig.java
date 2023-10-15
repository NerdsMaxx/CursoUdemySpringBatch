package com.springbatch.processadorvalidacao.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
//import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorvalidacao.dominio.Cliente;

@Configuration
public class ProcessadorValidacaoProcessorConfig {

	private final Set<String> emailSet = new HashSet<>();

	private final Validator<Cliente> validator = (cliente) -> {
		final String email = cliente.getEmail();

		if (emailSet.contains(email)) {
			throw new ValidationException(String.format("O cliente %s já foi processado!", email));
		}

		emailSet.add(email);
	};

	@Bean
	public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() throws Exception {
		return new CompositeItemProcessorBuilder<Cliente, Cliente>()
				.delegates(beanValidatingItemProcessor(), validatingItemProcessor())
				.build();
	}

	private BeanValidatingItemProcessor<Cliente> beanValidatingItemProcessor() throws Exception {
		final BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
		processor.setFilter(true);
		processor.afterPropertiesSet();
		
		return processor;
	}

	private ValidatingItemProcessor<Cliente> validatingItemProcessor() {

		final ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator);
		processor.setFilter(true);
		return processor;
	}

//	return new Validator<Cliente>() {
//
//		@Override
//		public void validate(Cliente cliente) throws ValidationException {
//			final String email = cliente.getEmail();
//			
//			if(emailSet.contains(email)) {
//				throw new ValidationException(String.format("O cliente %s já foi processado!", email));
//			}
//			
//			emailSet.add(email);
//		}
//		
//	};
}