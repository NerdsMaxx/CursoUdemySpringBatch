package com.springbatch.processadorclassifier.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorclassifier.dominio.Cliente;

@Configuration
public class ProcessadorClassifierProcessorConfig {
	
	@SuppressWarnings("rawtypes")
	@Bean
	public ItemProcessor processadorClassifierProcessor() {
		return new ClassifierCompositeItemProcessorBuilder<>()
				.classifier(classifier())
				.build();
	}

	private Classifier classifier() {
		return new Classifier<Object, ItemProcessor>() {
			@Override
			public ItemProcessor classify(Object classifiable) {
				if (classifiable instanceof Cliente) {
					return new ClienteProcessor();
				}
				else {
					return new TransacaoProcessor();
				}
			}
		};
	}

}
