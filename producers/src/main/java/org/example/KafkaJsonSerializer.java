package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class KafkaJsonSerializer<T> implements Serializer<T> {


    @Override
    public byte[] serialize(String topic, T data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            log.error("Error while serialize message ", e);
        }
        return retVal;
    }
}
