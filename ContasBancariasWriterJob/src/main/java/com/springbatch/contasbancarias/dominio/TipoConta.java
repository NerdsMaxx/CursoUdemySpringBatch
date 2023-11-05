package com.springbatch.contasbancarias.dominio;

import static java.util.Objects.isNull;

public enum TipoConta {
    PRATA,
    OURO,
    PLATINA,
    DIAMANTE,
    INVALIDA;
    
    public static TipoConta fromFaixaSalarial(Double faixaSalarial) {
        if (isNull(faixaSalarial)) {return INVALIDA;}
        if (faixaSalarial <= 3000) {return PRATA;}
        if (faixaSalarial > 3000 && faixaSalarial <= 5000) {return OURO;}
        if (faixaSalarial > 5000 && faixaSalarial <= 10000) {return PLATINA;}
        
        return DIAMANTE;
    }
}