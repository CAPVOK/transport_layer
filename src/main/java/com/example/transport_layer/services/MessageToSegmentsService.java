package com.example.transport_layer.services;

import com.example.transport_layer.kafka.KafkaProducerService;
import com.example.transport_layer.models.MessageDto;
import com.example.transport_layer.models.SegmentDto;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Krasnov
 */
@Service
@RequiredArgsConstructor
public class MessageToSegmentsService {

    @Value("${messageSize}")
    private Integer segmentSize;

    private final SendService sendService;
    private final KafkaProducerService kafkaProducerService;

    public List<SegmentDto> messageToSegmentsAndSend(MessageDto messageDto){
        List<SegmentDto> segmentList = new ArrayList<>();
        String sender = messageDto.getSender();
        String timestamp = messageDto.getTimestamp();
        String message = messageDto.getMessage();

        List<String> list = messageToSegments(message);
        Integer listSize = list.size();
        Integer number = 1;
        for(String segment : list){
            SegmentDto segmentDto = new SegmentDto(
                    sender,
                    timestamp,
                    listSize,
                    number,
                    segment);
            segmentList.add(segmentDto);
            var res = sendService.sendPostRequest("http://localhost:8081/api/datalink", segmentDto);
            //kafkaProducerService.sendMessage(segmentDto);
            number++;
        }
        return segmentList;
    }

    private List<String> messageToSegments(String message){
        List<String> res = new ArrayList<>();
        try {
            byte[] utf8Bytes = message.getBytes("UTF-8");
            int chunkSize = segmentSize;

            for (int i = 0; i < utf8Bytes.length; i += chunkSize) {
                int length = Math.min(chunkSize, utf8Bytes.length - i);
                byte[] chunk = new byte[length];
                System.arraycopy(utf8Bytes, i, chunk, 0, length);
                String chunkString = new String(chunk, "UTF-8");
                res.add(chunkString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
