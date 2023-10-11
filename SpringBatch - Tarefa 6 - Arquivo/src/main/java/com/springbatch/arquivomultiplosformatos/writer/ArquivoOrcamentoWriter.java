package com.springbatch.arquivomultiplosformatos.writer;

import com.springbatch.arquivomultiplosformatos.dominio.Orcamento;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.groupingBy;

@Component
public class ArquivoOrcamentoWriter implements ItemWriter<Orcamento> {
    
    @Override
    public void write(final List<? extends Orcamento> orcamentoList) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        final Map<String,List<Orcamento>> orcamentosPorDespesaMap;
        orcamentosPorDespesaMap = orcamentoList.stream().collect(groupingBy(Orcamento::getDespesa));
        
        final TreeSet<Map.Entry<String,List<Orcamento>>> orcamentosPorDespesaSet;
        orcamentosPorDespesaSet = new TreeSet<>(Map.Entry.comparingByKey());
        orcamentosPorDespesaSet.addAll(orcamentosPorDespesaMap.entrySet());
        
        for (final Map.Entry<String,List<Orcamento>> orcamentoEntry : orcamentosPorDespesaSet) {
            final List<Orcamento> orcamentoList1 = orcamentoEntry.getValue();
            orcamentoList1.sort(Comparator.naturalOrder());
            
            final Orcamento orcamento = orcamentoList1.get(0);
            final long valorTotal = orcamentoList1.stream().mapToLong(Orcamento::getValor).sum();
            
            out.println("---- Demonstrativo orçamentário ----");
            out.printf("[%d] %s - R$ %d,00%n",
                       orcamento.getCodigo(), orcamento.getDespesa(), valorTotal);
            
            for (final Orcamento orcamento1 : orcamentoList1) {
                out.printf("\t [%s] %s - R$ %d,00%n",
                           dtf.format(orcamento1.getData()), orcamento1.getItem(), orcamento1.getValor());
            }
        }
        
    }
}