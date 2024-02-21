package com.example.transport_layer.models;

import lombok.*;

/**
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String sender;
    private String timestamp;
    private String message;
}
