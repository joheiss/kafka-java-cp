package com.jovisco.dispatch.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;

import com.jovisco.dispatch.message.OrderCreated;
import com.jovisco.dispatch.util.TestEventData;

public class DispatchServiceTest {
    
    private DispatchService service;

    @BeforeEach
    void setUp() {
        service = new DispatchService();
    }

    @Test
    void process() {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        service.process(testEvent);
    }
}
