package com.springbatch.enviopromocoesemail.dominio;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private Double preco;
}