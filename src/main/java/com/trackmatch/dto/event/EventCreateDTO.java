package com.trackmatch.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.enums.EventStatus;
import com.trackmatch.dto.user.UserDTO;
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

    // TODO: Alterar o ApplicationEntity pelo ApplicationDTO após criação
    @Builder.Default
    private List<ApplicationEntity> applications = new ArrayList<>();
}
