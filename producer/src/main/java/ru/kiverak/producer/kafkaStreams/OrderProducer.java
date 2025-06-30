package ru.kiverak.producer.kafkaStreams;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.kiverak.producer.Order;

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

    @Scheduled(fixedDelay = 1000)
    public void send() {
        long orderId = orderIdCounter.incrementAndGet();
        Order order = new Order(String.valueOf(orderId), "Scheduled Widget", 3);
        boolean success = streamBridge.send("sendOrder-out-0", order);
        if (success) {
            log.info("✅ Order sent: {}", order);
        } else {
            log.info("❌ Failed to send order: {}", order);
        }
    }
}
