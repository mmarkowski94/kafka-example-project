package org.example;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


public class Main {

    private final static String TOPIC_NAME = "users";
    private final static String BOOTSTRAP_SERVERS = "127.0.0.1:9092";

    public static void main(String[] args) {

        Properties producerProperties = createProducerProperties();

        KafkaProducer<String, User> producer = new KafkaProducer<>(producerProperties);

        Thread thread = new Thread(new KafkaProducerRunnable(producer, TOPIC_NAME));
        thread.start();
    }

    private static Properties createProducerProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
        return props;
    }
}