package com.springbatch.contasbancarias.writer;

import com.springbatch.contasbancarias.dominio.FaturaCartaoCredito;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

public class TotalTransacoesFooterCallback implements FlatFileFooterCallback {
    private double total = 0.0;
    
    @Override
    public void writeFooter(Writer writer) throws IOException {
        final NumberFormat nf = NumberFormat.getCurrencyInstance();
        writer.write(String.format("\n%121s", "Total: " + nf.format(total)));
    }
    
    @BeforeWrite
    public void beforeWrite(List<FaturaCartaoCredito> faturas) {
        for (final FaturaCartaoCredito faturaCartaoCredito : faturas) {
            total += faturaCartaoCredito.getTotal();
        }
    }
    
    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        total = 0.0;
    }
}