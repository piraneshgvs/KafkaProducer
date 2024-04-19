package com.kafka.producer.controller;


import com.kafka.producer.config.ProducerConfig;
import com.kafka.producer.model.EventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {

    private ProducerConfig producerConfig;

    @Autowired
    public ProducerController(ProducerConfig producerConfig){
        this.producerConfig = producerConfig;
    }

    @PostMapping("/kafka/setMessage")
    public ResponseEntity<?> messageSenter(@RequestBody EventData eventData){
        producerConfig.sendMessage(eventData);
        return ResponseEntity.ok("Success!!!");
    }
}
