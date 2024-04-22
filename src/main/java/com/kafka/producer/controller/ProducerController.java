package com.kafka.producer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.config.ProducerConfig;
import com.kafka.producer.model.EventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class ProducerController {

    private ProducerConfig producerConfig;

    private int count = 0;

    @Autowired
    public ProducerController(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
    }

//    @PostMapping("/kafka/setMessage")
//    public ResponseEntity<?> messageSenter(@RequestBody EventData eventData){
//        producerConfig.sendMessage(eventData);
//        return ResponseEntity.ok("Success!!!");
//    }

//    @PostMapping("/kafka/setMessage")
//    public void messageSenter(){
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//
//            // Read JSON array from file
//            File jsonFile = new ClassPathResource("data.json").getFile();
//            JsonNode jsonArray = mapper.readTree(jsonFile);
//                                for (JsonNode element : jsonArray) {
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        EventData eventData = objectMapper.treeToValue(element, EventData.class);
//                        producerConfig.sendMessage(eventData);
//                    }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @PostMapping("/kafka/setMessage")
    public void messageSenter() {
        long duration = 1 * 60 * 1000;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                subscribeTopic();
            }
        }, 0, 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                System.out.println("Function calls stopped after 3 minutes.");
            }
        }, duration);
    }

    private void subscribeTopic() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            File jsonFile = new ClassPathResource("data.json").getFile();
            JsonNode jsonArray = mapper.readTree(jsonFile);
            JsonNode element = jsonArray.get(count);
            ObjectMapper objectMapper = new ObjectMapper();
            EventData eventData = objectMapper.treeToValue(element, EventData.class);
            producerConfig.sendMessage(eventData);
            count++;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

