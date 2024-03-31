package com.example.transport_layer.kafka;

import com.example.transport_layer.models.MessageDto;
import com.example.transport_layer.models.MessageDtoWithError;
import com.example.transport_layer.models.SegmentDto;
import com.example.transport_layer.services.SendService;
import lombok.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Vladimir Krasnov
 */
@Service
public class KafkaConsumerService {
    @Autowired
    private SendService api;

    private static final String TOPIC_NAME = "main_topic";

    private static final Map<String, List<SegmentDto>> map = new ConcurrentHashMap<>();

    @KafkaListener(topics = TOPIC_NAME, groupId = "1")
    public void consumeMessage(ConsumerRecord<String, SegmentDto> message) {
        SegmentDto segment = message.value();
        if(map.containsKey(segment.getTimestamp())){
            map.get(segment.getTimestamp()).add(segment);
        }else map.put(segment.getTimestamp(), new ArrayList<>(List.of(segment)));
    }

    @Scheduled(fixedRate = 1000)
    public void executeTask() {
        map.forEach((k,v)-> {
            if(v.get(0).getAmountOfSegments().equals(v.size())){
                Collections.sort(v);
                String message = v.stream().map(SegmentDto::getMessage).collect(Collectors.joining());
                MessageDtoWithError dto = new MessageDtoWithError(v.get(0).getSender(), v.get(0).getTimestamp(), message , false);
                api.sendPostRequest( "http://localhost:5001/api/send-message", dto);
            }else {
                MessageDtoWithError dto = new MessageDtoWithError(v.get(0).getSender(), v.get(0).getTimestamp(), null , true);
                api.sendPostRequest( "http://localhost:5001/api/send-message", dto);
            }

        });
        map.clear();
    }
}
