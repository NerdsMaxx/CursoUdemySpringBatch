package com.springbatch.enviopromocoesemail.processor;

import com.springbatch.enviopromocoesemail.dominio.Cliente;
import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;
import com.springbatch.enviopromocoesemail.dominio.Produto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component
public class ProcessarEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente,SimpleMailMessage> {
    
    @Override
    public SimpleMailMessage process(final InteresseProdutoCliente interesseProdutoCliente) throws InterruptedException {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("xpto@no-reply.com");
        email.setTo(interesseProdutoCliente.getCliente().getEmail());
        email.setSubject("Promoção Imperdível!!!!");
        email.setText(gerarTextoPromocao(interesseProdutoCliente));
        
        Thread.sleep(2000);
        
        return email;
    }
    
    private String gerarTextoPromocao(final InteresseProdutoCliente interesseProdutoCliente) {
        final Cliente cliente = interesseProdutoCliente.getCliente();
        final Produto produto = interesseProdutoCliente.getProduto();
        final NumberFormat nf = NumberFormat.getCurrencyInstance();
        
        return "Olá, " + cliente.getNome() + "!\n\n" +
               "Essa promoção pode ser do seu interesse:\n\n" +
               produto.getNome() + " - " + produto.getDescricao() + "\n\n" +
               "Por apenas: " + nf.format(produto.getPreco()) + "!";
    }
}