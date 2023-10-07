package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Orcamento;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class ArquivoOrcamentoReader implements ItemStreamReader<Orcamento>, ResourceAwareItemReaderItemStream<Orcamento> {
    
    private FlatFileItemReader<Object> delegate;
    
    public ArquivoOrcamentoReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public Orcamento read() throws Exception {
        return (Orcamento) delegate.read();
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
    
    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}