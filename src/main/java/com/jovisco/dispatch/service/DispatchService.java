package com.jovisco.dispatch.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jovisco.dispatch.message.DispatchPreparing;
import com.jovisco.dispatch.message.OrderCreated;
import com.jovisco.dispatch.message.OrderDispatched;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispatchService {
    
    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderCreated orderCreated) throws Exception {
        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
            .orderId(orderCreated.getOrderId())
            .build();

        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, dispatchPreparing).get();
        
        OrderDispatched orderDispatched = OrderDispatched.builder()
            .orderId(orderCreated.getOrderId())
            .build();

        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();
    }
}
