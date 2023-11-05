package com.springbatch.contasbancarias.writer;

import com.springbatch.contasbancarias.dominio.FaturaCartaoCredito;
import com.springbatch.contasbancarias.dominio.Transacao;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Configuration
public class ArquivoFaturaCartaoCreditoWriterConfig {
    
    @Bean
    public MultiResourceItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito() {
        return new MultiResourceItemWriterBuilder<FaturaCartaoCredito>()
                .name("escreverFaturaCartaoCredito")
                .resource(new FileSystemResource("files/fatura"))
                .itemCountLimitPerResource(1)
                .resourceSuffixCreator(index -> index + ".txt")
                .delegate(arquivoFaturaCartaoCredito())
                .build();
    }
    
    private ResourceAwareItemWriterItemStream<? super FaturaCartaoCredito> arquivoFaturaCartaoCredito() {
        return new FlatFileItemWriterBuilder<FaturaCartaoCredito>()
                .name("arquivoFaturaCartaoCredito")
                .resource(new FileSystemResource("files/fatura.txt"))
                .lineAggregator(lineAggregator())
                .headerCallback(headerCallback())
                .footerCallback(footerCallback())
                .build();
    }
    
    @Bean
    public FlatFileFooterCallback footerCallback() {
        return new TotalTransacoesFooterCallback();
    }
    
    private FlatFileHeaderCallback headerCallback() {
        return (final Writer writer) -> {
            writer.append(String.format("%121s\n", "Cartão XPTO"))
                  .append(String.format("%121s\n\n", "Rua Vergueiro, 131"));
        };
    }
    
    private LineAggregator<FaturaCartaoCredito> lineAggregator() {
        return (final FaturaCartaoCredito faturaCartaoCredito) -> {
            final String nome = faturaCartaoCredito.getCliente().getNome();
            final int numeroCartaoCredito = faturaCartaoCredito.getCartaoCredito().getNumeroCartaoCredito();
            
            final String tracos = Stream.generate(() -> "-").limit(121).collect(joining());
            
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Nome: " + nome + "\n")
                         .append("Endereço: " + numeroCartaoCredito + "\n\n\n")
                         .append(tracos)
                         .append("\nDATA DESCRICAO VALOR\n")
                         .append(tracos);
            
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            final NumberFormat nf = NumberFormat.getCurrencyInstance();
            
            for (final Transacao transacao : faturaCartaoCredito.getTransacoes()) {
                stringBuilder.append(String.format("\n[%10s] ", sdf.format(transacao.getData())))
                             .append(String.format("- %80s - ", transacao.getDescricao()))
                             .append(nf.format(transacao.getValor()));
            }
            
            return stringBuilder.toString();
        };
    }
}