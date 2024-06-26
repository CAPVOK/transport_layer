package com.example.transport_layer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SegmentDto implements Serializable, Comparable<SegmentDto> {
    private String sender;
    private String timestamp;
    private Integer amountOfSegments;
    private Integer segmentNum;
    private String message;

    @Override
    public int compareTo(SegmentDto otherSegment) {
        return this.segmentNum.compareTo(otherSegment.getSegmentNum());
    }
}
