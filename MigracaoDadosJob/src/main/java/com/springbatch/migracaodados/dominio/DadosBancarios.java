package com.springbatch.migracaodados.dominio;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class DadosBancarios {
    private int id;
    private int pessoaId;
    private int agencia;
    private int conta;
    private int banco;
}