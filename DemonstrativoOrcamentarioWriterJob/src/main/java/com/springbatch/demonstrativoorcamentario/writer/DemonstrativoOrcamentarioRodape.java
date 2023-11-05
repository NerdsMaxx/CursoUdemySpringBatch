package com.springbatch.demonstrativoorcamentario.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;

@Component
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {

	private double totalGeral = 0.0;
	
	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.append("\n");
		writer.append("\t\t\t\t\t\t\t  Total: " + NumberFormat.getCurrencyInstance().format(totalGeral) + '\n');
		writer.append("\t\t\t\t\t\t\t  Código de Autenticação: fkyew6868fewjfhjjewf\n");
	}
	
	@BeforeWrite
	public void beforeWrite(List<GrupoLancamento> grupoLancamentoList) {
		grupoLancamentoList.forEach(grupoLancamento -> totalGeral += grupoLancamento.getTotal());
	}
	
	@AfterChunk
	public void afterChunk(ChunkContext context) {
		totalGeral = 0.0;
	}
}