package com.springbatch.contasbancarias.reader;

import com.springbatch.contasbancarias.dominio.CartaoCredito;
import com.springbatch.contasbancarias.dominio.FaturaCartaoCredito;
import com.springbatch.contasbancarias.dominio.Transacao;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {
    private final ItemStreamReader<Transacao> delegate;
    private Transacao transacaoAtual;
    
    
    @Override
    public FaturaCartaoCredito read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (isNull(transacaoAtual)) {
            transacaoAtual = delegate.read();
        }
        
        Transacao transacao = transacaoAtual;
        transacaoAtual = null;
        
        if (nonNull(transacao)) {
            final CartaoCredito cartaoCredito = transacao.getCartaoCredito();
            
            final FaturaCartaoCredito faturaCartaoCredito;
            faturaCartaoCredito = FaturaCartaoCredito
                    .builder()
                    .cartaoCredito(cartaoCredito)
                    .cliente(cartaoCredito.getCliente())
                    .build();
            
            faturaCartaoCredito.adicionarTransacao(transacao);
            
            while (isTransacaoRelacionada(transacao)) {
                faturaCartaoCredito.adicionarTransacao(transacaoAtual);
            }
            
            return faturaCartaoCredito;
        }
        
        return null;
    }
    
    private boolean isTransacaoRelacionada(Transacao transacao) {
        return nonNull(peek()) && transacao.equalsNumeroCartaoCredito(transacaoAtual);
    }
    
    private Transacao peek() {
        try {
            transacaoAtual = delegate.read();
            return transacaoAtual;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }
    
    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }
    
    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}