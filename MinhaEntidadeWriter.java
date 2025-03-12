package com.example.batch.writer;

import com.example.batch.model.MinhaEntidade;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;

@Configuration
public class MinhaEntidadeWriter {

    @Bean
    public JdbcBatchItemWriter<MinhaEntidade> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<MinhaEntidade>()
                .dataSource(dataSource)
                .sql("UPDATE minha_tabela SET status = :status WHERE id = :id")
                .itemSqlParameterSourceProvider(entidade -> {
                    MapSqlParameterSource params = new MapSqlParameterSource();
                    params.addValue("id", entidade.getId());
                    params.addValue("status", "expirado");
                    return params;
                })
                .namedParameters()
                .build();
    }
}
