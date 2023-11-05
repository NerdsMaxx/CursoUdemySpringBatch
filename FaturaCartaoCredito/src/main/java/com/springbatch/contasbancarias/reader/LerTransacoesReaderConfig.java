package com.springbatch.contasbancarias.reader;

import com.springbatch.contasbancarias.dominio.CartaoCredito;
import com.springbatch.contasbancarias.dominio.Cliente;
import com.springbatch.contasbancarias.dominio.Transacao;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;

@Configuration
public class LerTransacoesReaderConfig {
    
    @Bean
    public JdbcCursorItemReader<Transacao> lerTransacoesReader(
            @Qualifier("appDataSource") final DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Transacao>()
                .name("lerTransacoesReader")
                .dataSource(dataSource)
                .sql("select * from transacao t " +
                     "join cartao_credito cc " +
                     "on t.numero_cartao_credito = cc.numero_cartao_credito " +
                     "order by cc.numero_cartao_credito")
                .rowMapper(rowMapperTransacao())
                .build();
    }
    
    private RowMapper<Transacao> rowMapperTransacao() {
        return (final ResultSet rs, final int rowNum) -> {
            final Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("cliente"));
            
            final CartaoCredito cartaoCredito = CartaoCredito
                    .builder()
                    .numeroCartaoCredito(rs.getInt("numero_cartao_credito"))
                    .cliente(cliente)
                    .build();
            
            return Transacao
                    .builder()
                    .id(rs.getInt("id"))
                    .cartaoCredito(cartaoCredito)
                    .data(rs.getDate("data"))
                    .valor(rs.getDouble("valor"))
                    .descricao(rs.getString("descricao"))
                    .build();
        };
    }
    
}