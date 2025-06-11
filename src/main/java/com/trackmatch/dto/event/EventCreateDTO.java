package com.trackmatch.dto.event;

import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.enums.EventStatus;
import com.trackmatch.dto.application.ApplicationResponseDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCreateDTO {
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String instrumentNeeded;

    private Long createdById;

    @Builder.Default
    private EventStatus status = EventStatus.OPEN;

    @Builder.Default
    private List<Long> applicationIds = new ArrayList<>();
}
