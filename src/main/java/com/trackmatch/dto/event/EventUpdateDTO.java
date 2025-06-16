package com.trackmatch.dto.event;

import com.trackmatch.domain.enums.EventStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateDTO {
    private String title;
    private String description;
    private LocalDateTime scheduled_at;
    private String location;
    private String instrumentNeeded;
    private EventStatus status;
}
