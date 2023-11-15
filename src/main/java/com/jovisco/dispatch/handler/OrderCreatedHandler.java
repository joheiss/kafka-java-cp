package com.jovisco.dispatch.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jovisco.dispatch.message.OrderCreated;
import com.jovisco.dispatch.service.DispatchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreatedHandler {
    
    private final DispatchService dispatchService;

    @KafkaListener(
        id = "orderConsumerClient",
        topics = "order.created",
        groupId = "dispatch.order.created.consumer",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreated payload) {
        log.info("ORDER CREATED Message received: " + payload);
        try {
            dispatchService.process(payload);
        } catch (Exception e) {
            log.error("Processing failed", e);
        }
    }
}
