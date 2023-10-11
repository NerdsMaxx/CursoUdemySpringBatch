package com.springbatch.arquivomultiplosformatos.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class Orcamento implements Comparable<Orcamento> {
    
    private Long codigo;
    private String despesa;
    private String item;
    private LocalDate data;
    private Long valor;
    
    public Long getCodigo() {
        return this.codigo;
    }
    
    public String getDespesa() {
        return this.despesa;
    }
    
    public String getItem() {
        return this.item;
    }
    
    public LocalDate getData() {
        return this.data;
    }
    
    public Long getValor() {
        return this.valor;
    }
    
    public void setCodigo(final Long codigo) {
        this.codigo = requireNonNull(codigo);
    }
    
    public void setDespesa(final String despesa) {
        this.despesa = requireNonNull(despesa);
    }
    
    public void setItem(final String item) {
        this.item = requireNonNull(item);
    }
    
    public void setData(final String data) {
        requireNonNull(data);
        this.data = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(data, LocalDate::from);
    }
    
    public void setValor(final Long valor) {
        this.valor = requireNonNull(valor);
    }
    
    @Override
    public int compareTo(Orcamento o) {
        return Comparator.comparing(Orcamento::getDespesa)
                         .thenComparing(Orcamento::getData)
                         .thenComparing(Orcamento::getValor)
                         .compare(this, o);
    }
}