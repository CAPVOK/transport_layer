package com.example.transport_layer.services;

import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vladimir Krasnov
 */
@Service
@RequiredArgsConstructor
public class SendService {

    private final RestTemplate restTemplate;
    public Object sendPostRequest(String url, Object data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(data, headers);

        var res = restTemplate.postForObject(url, requestEntity, Object.class);
        return res;
    }
}
