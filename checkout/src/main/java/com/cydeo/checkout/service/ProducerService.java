package com.cydeo.checkout.service;

import com.cydeo.checkout.dto.CheckoutDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public String sendMessage(CheckoutDTO checkoutDTO) throws JsonProcessingException {
        String stringMessage = objectMapper.writeValueAsString(checkoutDTO);
        kafkaTemplate.send("order", stringMessage);
        return "message sent";
    }

}
