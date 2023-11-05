package com.springbatch.contasbancarias.dominio;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Transacao {
    private int id;
    private CartaoCredito cartaoCredito;
    private String descricao;
    private Double valor;
    private Date data;
    
    public boolean equalsNumeroCartaoCredito(final Transacao transacao) {
        return cartaoCredito.getNumeroCartaoCredito() == transacao.getCartaoCredito().getNumeroCartaoCredito();
    }
}