package ru.kiverak.consumer;

public record Order(
        String orderId,
        String product,
        Integer quantity
) {
}
