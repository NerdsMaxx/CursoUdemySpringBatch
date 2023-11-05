package com.springbatch.enviopromocoesemail.dominio;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cliente {
    private int id;
    private String nome;
    private String email;
}