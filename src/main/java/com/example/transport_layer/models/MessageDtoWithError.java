package com.example.transport_layer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDtoWithError {
    private String sender;
    private String timestamp;
    private String message;
    private Boolean error;
}
