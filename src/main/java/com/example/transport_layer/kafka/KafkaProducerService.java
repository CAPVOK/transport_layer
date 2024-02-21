package com.example.transport_layer.kafka;

import com.example.transport_layer.models.SegmentDto;
import lombok.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Vladimir Krasnov
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, SegmentDto> kafkaTemplate;
    private static final String TOPIC_NAME = "main_topic";

    public void sendMessage(SegmentDto message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}
