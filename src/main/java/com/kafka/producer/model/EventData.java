package com.kafka.producer.model;


import org.springframework.stereotype.Component;

import java.util.Map;


public class EventData {

    private int id;
    private String appName;
    private String environment;
    private Map<String, String> dynamicValues;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Map<String, String> getDynamicValues() {
        return dynamicValues;
    }

    public void setDynamicValues(Map<String, String> dynamicValues) {
        this.dynamicValues = dynamicValues;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", environment='" + environment + '\'' +
                ", dynamicValues=" + dynamicValues +
                '}';
    }

    public EventData(int id, String appName, String environment, Map<String, String> dynamicValues) {
        this.id = id;
        this.appName = appName;
        this.environment = environment;
        this.dynamicValues = dynamicValues;
    }

    public EventData() {
        super();
    }
}
