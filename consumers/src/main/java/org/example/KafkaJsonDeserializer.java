package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class KafkaJsonDeserializer<T> implements Deserializer<T> {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final Class<T> type;

    public KafkaJsonDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        T obj = null;
        try {
            obj = mapper.readValue(bytes, type);
        } catch (Exception e) {
            log.error("Error while deserializing message from topic '{}'", topic, e);
        }
        return obj;
    }
}
