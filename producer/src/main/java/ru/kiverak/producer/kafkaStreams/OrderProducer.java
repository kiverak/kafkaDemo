package ru.kiverak.producer.kafkaStreams;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import ru.kiverak.producer.Order;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class OrderProducer {

    @Getter
    @Setter
    private AtomicLong orderIdCounter;

    private final StreamBridge streamBridge;

    public OrderProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        this.orderIdCounter = new AtomicLong();
    }

//    @Scheduled(fixedDelay = 1000)
//    public void send() {
//        long orderId = orderIdCounter.incrementAndGet();
//        Order order = new Order(String.valueOf(orderId), "Scheduled Widget", 3);
//        boolean success = streamBridge.send("sendOrder-out-0", order);
//        if (success) {
//            log.info("✅ Order sent: {}", order);
//        } else {
//            log.info("❌ Failed to send order: {}", order);
//        }
//    }

    @Scheduled(fixedDelay = 1000)
    public void sendOrderStatus() {
        long orderId = orderIdCounter.incrementAndGet();
        String status = new Random().nextBoolean() ? "order started" : "order completed";
        Order order = new Order(String.valueOf(orderId), "Scheduled Widget", 3, status);

        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("orderId", orderId)
                .build();

        boolean success = streamBridge.send("sendOrderStatus-out-0", message);

        if (success) {
            log.info("✅ Order status sent to partitioned topic: orderId={}, status='{}'", orderId, status);
        } else {
            log.warn("❌ Failed to send order status: orderId={}, status='{}'", orderId, status);
        }
    }
}
