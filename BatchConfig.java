package com.example.batch.config;

import com.example.batch.model.MinhaEntidade;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Step atualizarStatusStep(StepBuilderFactory stepBuilderFactory,
                                    ItemReader<MinhaEntidade> reader,
                                    ItemWriter<MinhaEntidade> writer,
                                    ItemWriter<MinhaEntidade> kafkaWriter) {
        return stepBuilderFactory.get("atualizarStatusStep")
                .<MinhaEntidade, MinhaEntidade>chunk(10)
                .reader(reader)
                .writer(writer)
                .writer(kafkaWriter) // Envia para Kafka
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("atualizarStatusJob")
                .start(step)
                .build();
    }
}
