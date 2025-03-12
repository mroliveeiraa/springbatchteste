package com.example.batch.writer;

import com.example.batch.model.MinhaEntidade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaWriter implements ItemWriter<MinhaEntidade> {

    private final KafkaTemplate<String, MinhaEntidade> kafkaTemplate;

    @Override
    public void write(List<? extends MinhaEntidade> items) {
        for (MinhaEntidade entidade : items) {
            kafkaTemplate.send("meu-topico-avro", entidade);
        }
    }
}
