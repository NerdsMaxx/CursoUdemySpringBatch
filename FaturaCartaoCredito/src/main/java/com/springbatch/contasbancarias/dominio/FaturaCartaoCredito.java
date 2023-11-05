package com.springbatch.contasbancarias.dominio;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@Data
public class FaturaCartaoCredito {
    private Cliente cliente;
    private CartaoCredito cartaoCredito;
    
    @Builder.Default
    private List<Transacao> transacoes = new ArrayList<>();
    
    public void adicionarTransacao(final Transacao transacao) {
        transacoes.add(transacao);
    }
    
    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }
    
    public double getTotal() {
        return transacoes.stream().mapToDouble(Transacao::getValor).sum();
    }
}