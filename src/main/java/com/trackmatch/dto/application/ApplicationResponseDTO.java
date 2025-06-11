package com.trackmatch.dto.application;

import com.trackmatch.domain.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDTO {
    private Long id;
    private Long userId;
    private Long eventId;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
}
