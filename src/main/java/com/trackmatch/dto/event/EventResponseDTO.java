package com.trackmatch.dto.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.enums.EventStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String instrumentNeeded;

    private Long createdById;            // ← expõe só o ID

    @Builder.Default
    private EventStatus status = EventStatus.OPEN;

    @Builder.Default
    private List<ApplicationEntity> applications = new ArrayList<>();
}
