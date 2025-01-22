package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class Main {

    private final static String TOPIC_NAME = "users";
    private final static String GROUP_ID = "group-1";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties consumerProperties = createConsumerProperties();

        KafkaConsumer<String, UserInfo> consumer = new KafkaConsumer<String, UserInfo>(
                consumerProperties,
                new StringDeserializer(),
                new KafkaJsonDeserializer<UserInfo>(UserInfo.class));

        Thread thread = new Thread(new KafkaConsumerRunnable(consumer, TOPIC_NAME));
        thread.start();
    }

    private static Properties createConsumerProperties() {
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        return consumerProps;
    }
}