package com.jovisco.dispatch.service;

import org.springframework.stereotype.Service;

import com.jovisco.dispatch.message.OrderCreated;

@Service
public class DispatchService {
    
    public void process(OrderCreated payload) {
        // no-op
    }
}
