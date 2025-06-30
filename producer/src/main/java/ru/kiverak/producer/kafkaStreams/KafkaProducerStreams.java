package ru.kiverak.producer.kafkaStreams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KafkaProducerStreams {

//    @Bean(name = "sendOrder")
//    @PollableBean
//    public Supplier<Order> sendOrder() {
//        return () -> {
//            Order order = new Order("1", "Product name", 12);
//            log.info("Order sent to kafka: id={}", order.orderId());
//            return order;
//        };
//    }
//
//    @Bean(name = "sendOrderFunction")
//    public Function<Order, Order> sendOrderFunction() {
//        return order -> {
//            log.info("📨 [Function] Received order: {}", order);
//            return order;
//        };
//    }

}
