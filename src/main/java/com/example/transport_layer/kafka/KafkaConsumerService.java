package com.example.transport_layer.kafka;

import com.example.transport_layer.models.SegmentDto;
import lombok.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Vladimir Krasnov
 */
@Service
public class KafkaConsumerService {

    private static final String TOPIC_NAME = "main_topic";

    @KafkaListener(topics = TOPIC_NAME, groupId = "1")
    public void consumeMessage(ConsumerRecord<String, SegmentDto> message) {
        System.out.println("Received message: " + message.value().getMessage());
    }
}
