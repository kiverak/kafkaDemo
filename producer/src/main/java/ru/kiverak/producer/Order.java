package ru.kiverak.producer;

public record Order(
        String orderId,
        String product,
        Integer quantity
) { }
