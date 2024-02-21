package com.example.transport_layer.controllers;

import com.example.transport_layer.models.MessageDto;
import com.example.transport_layer.models.SegmentDto;
import com.example.transport_layer.services.MessageToSegmentsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vladimir Krasnov
 */
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("")
@SecurityRequirement(name = "bearerAuth")
public class SegmentController {

    private final MessageToSegmentsService messageToSegmentsService;

    @PostMapping("/send-message")
    public ResponseEntity<List<SegmentDto>> sendMessage(@RequestBody MessageDto message){
        List<SegmentDto> res = messageToSegmentsService.messageToSegmentsAndSend(message);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

}
