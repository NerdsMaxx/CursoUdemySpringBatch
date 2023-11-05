package com.springbatch.contasbancarias.dominio;

import lombok.Builder;
import lombok.Data;

@Data
public class Cliente {
    private int id;
    private String nome;
    private String endereco;
}