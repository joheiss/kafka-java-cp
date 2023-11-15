package com.jovisco.dispatch.handler;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static java.util.UUID.randomUUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jovisco.dispatch.message.OrderCreated;
import com.jovisco.dispatch.service.DispatchService;
import com.jovisco.dispatch.util.TestEventData;

public class OrderCreatedHandlerTest {
    
    private OrderCreatedHandler handler;
    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock = mock(DispatchService.class);
        handler = new OrderCreatedHandler(dispatchServiceMock);
    }

    @Test
    void listen_Success() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        handler.listen(testEvent);
        verify(dispatchServiceMock, times(1)).process(testEvent);
    }

    @Test
    void listen_Fail() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Service failed")).when(dispatchServiceMock).process(testEvent);
        
        handler.listen(testEvent);
        verify(dispatchServiceMock, times(1)).process(testEvent);
    }
}
