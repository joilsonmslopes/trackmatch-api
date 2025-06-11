package com.trackmatch.dto.application;

import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.domain.enums.ApplicationStatus;
import com.trackmatch.dto.event.EventResponseDTO;
import com.trackmatch.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationCreateDTO {
    private Long userId;
    private Long eventId;

    @Builder.Default
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
