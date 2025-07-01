package ru.kiverak.consumer.kafkaStreams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kiverak.consumer.Order;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class KafkaStreamConsumer {

    @Bean
    public Consumer<Order> processOrder() {
        return order -> {
            log.info("Received: {} @ product: {}, quantity: {}", order.orderId(), order.product(), order.quantity());
        };
    }
}
