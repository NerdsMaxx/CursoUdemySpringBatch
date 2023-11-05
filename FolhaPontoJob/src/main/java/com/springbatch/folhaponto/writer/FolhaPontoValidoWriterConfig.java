package com.springbatch.folhaponto.writer;

import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.springbatch.folhaponto.dominio.FolhaPonto;

@Configuration
public class FolhaPontoValidoWriterConfig {

    @Bean
    public JdbcBatchItemWriter<FolhaPonto> folhaPontoJdbcWriter(
            @Qualifier("appDataSource") DataSource appDataSource) {
        return new JdbcBatchItemWriterBuilder<FolhaPonto>().dataSource(appDataSource)
                .sql("INSERT INTO folha_ponto (mes, ano, funcionario_id, registros_ponto) "
                        + "VALUES(?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter()).build();
    }

    private ItemPreparedStatementSetter<FolhaPonto> itemPreparedStatementSetter() {
        return (FolhaPonto folhaPonto, PreparedStatement ps) -> {
            ps.setInt(1, folhaPonto.getMes());
            ps.setInt(2, folhaPonto.getAno());
            ps.setInt(3, folhaPonto.getMatricula());
            ps.setString(4, folhaPonto.getRegistrosPontoTexto());
        };
    }
}