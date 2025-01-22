package org.example;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;

import java.util.concurrent.atomic.AtomicBoolean;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class KafkaProducerRunnable implements Runnable {
    AtomicBoolean closed = new AtomicBoolean(false);
    KafkaProducer<String, User> producer;
    String topicName;

    public KafkaProducerRunnable(KafkaProducer<String, User> producer, String topicName) {
        this.producer = producer;
        this.topicName = topicName;
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                User user = FakeUserFactory.createUser();
                ProducerRecord<String, User> record = new ProducerRecord<>(topicName, "key", user);
                //send data
                producer.send(record);
                log.info("Sent record: {}, {}", user.getName(), user.getSurname());
                producer.flush();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Sending interrupted", e);
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) throw e;
        } finally {
            producer.close();
        }
    }
}
