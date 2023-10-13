package com.springbatch.jdbccursorreader.mapper;

import com.springbatch.jdbccursorreader.dominio.Orcamento;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrcamentoMapper implements RowMapper<Orcamento> {
    
    @Override
    public Orcamento mapRow(ResultSet resultSet, int i) throws SQLException {
        final Orcamento orcamento = new Orcamento();
        orcamento.setCodigo(resultSet.getLong("codigonaturezadespesa"));
        orcamento.setDespesa(resultSet.getString("descricaonaturezadespesa"));
        orcamento.setItem(resultSet.getString("descricaolancamento"));
        orcamento.setData(resultSet.getString("datalancamento"));
        orcamento.setValor(resultSet.getLong("valorlancamento"));
        return orcamento;
    }
}