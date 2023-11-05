package com.springbatch.contasbancarias.processor;

import com.springbatch.contasbancarias.dominio.Cliente;
import com.springbatch.contasbancarias.dominio.FaturaCartaoCredito;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;

@Component
public class CarregarDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {
    private RestTemplate restTemplate = new RestTemplate();
    
    @Override
    public FaturaCartaoCredito process(final FaturaCartaoCredito faturaCartaoCredito) throws ValidationException {
        final int clienteId = faturaCartaoCredito.getCliente().getId();
        final String uri = "https://my-json-server.typicode.com/giuliana-bezerra/demo/profile/" + clienteId;
        
        final ResponseEntity<Cliente> clienteResponse = restTemplate.getForEntity(uri, Cliente.class);
        
        if(clienteResponse.getStatusCode() != HttpStatus.OK) {
            throw new ValidationException("Cliente não encontrado!");
        }
        
        faturaCartaoCredito.setCliente(clienteResponse.getBody());
        return faturaCartaoCredito;
    }
}