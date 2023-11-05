package com.springbatch.demonstrativoorcamentario.writer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentario.dominio.Lancamento;

//	@Bean
//	public ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter() {
//		return itens -> {
//			System.out.println("\n");
//			System.out.println(String.format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
//			System.out.println(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t HORA: %s", new SimpleDateFormat("HH:MM").format(new Date())));
//			System.out.println(String.format("\t\t\tDEMONSTRATIVO ORCAMENTARIO"));
//			System.out.println(String.format("----------------------------------------------------------------------------"));
//			System.out.println(String.format("CODIGO NOME VALOR"));
//			System.out.println(String.format("\t Data Descricao Valor"));
//			System.out.println(String.format("----------------------------------------------------------------------------"));
//
//			Double totalGeral = 0.0;
//			for (GrupoLancamento grupo : itens) {
//				System.out.println(String.format("[%d] %s - %s", grupo.getCodigoNaturezaDespesa(),
//						grupo.getDescricaoNaturezaDespesa(),
//						NumberFormat.getCurrencyInstance().format(grupo.getTotal())));
//				totalGeral += grupo.getTotal();
//				for (Lancamento lancamento : grupo.getLancamentos()) {
//					System.out.println(String.format("\t [%s] %s - %s", new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
//							NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
//				}
//			}
//			System.out.println("\n");
//			System.out.println(String.format("\t\t\t\t\t\t\t  Total: %s", NumberFormat.getCurrencyInstance().format(totalGeral)));
//			System.out.println(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s", "fkyew6868fewjfhjjewf"));
//		};
//	}

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {

	@StepScope
	@Bean
	public MultiResourceItemWriter<GrupoLancamento> multiDemonstrativoOrcamentarioWriter(
			@Value("#{jobParameters['demonstrativosOrcamentarios']}") Resource demonstrativosOrcamentarios,
			FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {
		return new MultiResourceItemWriterBuilder<GrupoLancamento>()
				.name("multiDemonstrativoOrcamentarioWriter")
				.resource(demonstrativosOrcamentarios)
				.delegate(demonstrativoOrcamentarioWriter)
				.resourceSuffixCreator(suffixCreator())
				.itemCountLimitPerResource(1)
				.build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
			@Value("#{jobParameters['demonstrativoOrcamentario']}") Resource demonstrativoOrcamentario,
			DemonstrativoOrcamentarioRodape rodapeCallback) {
		return new FlatFileItemWriterBuilder<GrupoLancamento>()
				.name("demonstrativoOrcamentarioWriter")
				.resource(demonstrativoOrcamentario)
				.lineAggregator(lineAggregator())
				.headerCallback(cabecalhoCallback())
				.footerCallback(rodapeCallback)
				.build();
	}
	
	private ResourceSuffixCreator suffixCreator() {
		return index -> index + ".txt";
	}

	private FlatFileHeaderCallback cabecalhoCallback() {
		return (writer) -> {
			final Date dataAtual = new Date();
			final String dataFormatado = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
			final String horarioFormatado = new SimpleDateFormat("HH:MM").format(dataAtual);
			
			writer.append("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: " + dataFormatado + '\n');
			writer.append("MÓDULO: ORÇAMENTO \t\t\t\t\t\t HORA: " + horarioFormatado + '\n');
			writer.append("\t\t\tDEMONSTRATIVO ORCAMENTARIO\n");
			writer.append("----------------------------------------------------------------------------\n");
			writer.append("CODIGO NOME VALOR\n");
			writer.append("\t Data Descricao Valor\n");
			writer.append("----------------------------------------------------------------------------\n");
		};
	}

	private LineAggregator<GrupoLancamento> lineAggregator() {
		return grupoLancamento -> {
			final Integer codigoDespesa = grupoLancamento.getCodigoNaturezaDespesa();
			final String descricaoDespesa = grupoLancamento.getDescricaoNaturezaDespesa();
			final String total = NumberFormat.getCurrencyInstance().format(grupoLancamento.getTotal());
			
			final StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("[" + codigoDespesa + "] " + descricaoDespesa + " - " + total + '\n');

			final List<Lancamento> lancamentoList = grupoLancamento.getLancamentos();
			final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			final NumberFormat nf = NumberFormat.getCurrencyInstance();

			for (Lancamento lancamento : lancamentoList) {
				stringBuilder.append("\t [" + sdf.format(lancamento.getData()) + "] " + 
			                         lancamento.getDescricao() + " - " + nf.format(lancamento.getValor()) + 
			                         '\n');
			}

			return stringBuilder.toString();
		};
	}
}