package com.springbatch.enviopromocoesemail.dominio;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InteresseProdutoCliente {
    private Cliente cliente;
    private Produto produto;
}