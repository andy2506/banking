package com.banking.banking.service;

import com.banking.banking.event.PublishRequest;
import com.banking.banking.event.PublishResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SnsClient {

        public PublishResponse publish(String message) {
            return PublishResponse.builder()
                    .response("Testing: " + message)
                    .build();
        }
    }

