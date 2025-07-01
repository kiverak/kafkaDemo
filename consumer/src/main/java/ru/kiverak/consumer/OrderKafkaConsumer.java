package ru.kiverak.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderKafkaConsumer.class);

//    @KafkaListener(topics = "orders")
//    public void consumeOrder1(ConsumerRecord<String, Order> record) {
//        log.info(
//                "Received order1: order={}, key={}, partition={}",
//                record.value(),
//                record.key(),
//                record.partition()
//        );
//    }
//
//    @KafkaListener(topics = "orders", groupId = "warehouse-group-new")
//    public void consumeOrder2(ConsumerRecord<String, Order> record) {
//        log.info(
//                "Received order2: order={}, key={}, partition={}",
//                record.value(),
//                record.key(),
//                record.partition()
//        );
//    }
//
//    @KafkaListener(topics = "my-topic-new")
//    public void consumeMyTopicNew(ConsumerRecord<String, Order> record) {
//        log.info(
//                "Received my-topic-new: order={}, key={}, partition={}",
//                record.value(),
//                record.key(),
//                record.partition()
//        );
//    }

    @KafkaListener(topics = "orders", groupId = "verifier")
    public void listen(ConsumerRecord<String, Order> record) {
        Order payload = record.value();
        log.info("📦 Received orderId={} with status='{}' from partition={}",
                payload.orderId(), payload.status(), record.partition());
    }
}
