package com.springbatch.migracaodados.dominio;

import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

import static java.util.Objects.nonNull;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private int idade;
    
    public boolean isValida() {
        return Strings.isNotBlank(nome) &&
               Strings.isNotBlank(email) &&
               nonNull(dataNascimento);
    }
}