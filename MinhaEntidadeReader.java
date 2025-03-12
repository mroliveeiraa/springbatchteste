package com.example.batch.reader;

import com.example.batch.model.MinhaEntidade;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class MinhaEntidadeReader {

    @Bean
    public JdbcPagingItemReader<MinhaEntidade> reader(DataSource dataSource) {
        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("id, nome, data_fim, status");
        queryProvider.setFromClause("from minha_tabela");
        queryProvider.setWhereClause("where data_fim < now()");

        return new JdbcPagingItemReaderBuilder<MinhaEntidade>()
                .name("minhaEntidadeReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(10)
                .rowMapper(new BeanPropertyRowMapper<>(MinhaEntidade.class))
                .build();
    }
}
