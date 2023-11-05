package com.springbatch.enviopromocoesemail.reader;

import com.springbatch.enviopromocoesemail.dominio.Cliente;
import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;
import com.springbatch.enviopromocoesemail.dominio.Produto;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;

@Configuration
public class InteresseProdutoClienteReaderConfig {
    
    @Bean
    public JdbcCursorItemReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader(
            @Qualifier("appDataSource") final DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<InteresseProdutoCliente>()
                .name("lerInteresseProdutoClienteReader")
                .dataSource(dataSource)
                .sql("select * from interesse_produto_cliente ipc " +
                     "join cliente c on c.id = ipc.cliente " +
                     "join produto p on p.id = ipc.produto")
                .rowMapper(rowMapper())
                .build();
    }
    
    private RowMapper<InteresseProdutoCliente> rowMapper() {
        return (final ResultSet rs, int rowNum) -> {
            final Cliente cliente = Cliente.builder()
                                           .id(rs.getInt("id"))
                                           .nome(rs.getString("nome"))
                                           .email(rs.getString("email"))
                                           .build();
            
            final Produto produto = Produto.builder()
                                           .id(rs.getInt(6))
                                           .nome(rs.getString(7))
                                           .descricao(rs.getString("descricao"))
                                           .preco(rs.getDouble("preco"))
                                           .build();
            
            return InteresseProdutoCliente.builder()
                                          .cliente(cliente)
                                          .produto(produto)
                                          .build();
        };
    }
}