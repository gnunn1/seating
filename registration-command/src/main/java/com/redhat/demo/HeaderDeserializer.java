package com.redhat.demo;

import java.nio.charset.StandardCharsets;

import org.apache.camel.component.kafka.serde.KafkaHeaderDeserializer;

public class HeaderDeserializer implements KafkaHeaderDeserializer {

    @Override
    public Object deserialize(String key, byte[] value) {
        if (key == "uber-trace-id") {
            return new String(value, StandardCharsets.UTF_8);
        } else {
            return value;
        }
    }
}