package com.kafka.producer.config;

import com.kafka.producer.model.EventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerConfig {

    private KafkaTemplate<String, EventData> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String key;

    @Autowired
    public ProducerConfig(KafkaTemplate<String, EventData> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EventData eventData){
        kafkaTemplate.send(key, eventData);
    }





}
