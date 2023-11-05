package com.springbatch.contasbancarias.dominio;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartaoCredito {
    private int numeroCartaoCredito;
    private Cliente cliente;
}