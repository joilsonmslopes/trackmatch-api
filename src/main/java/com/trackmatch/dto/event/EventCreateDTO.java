package com.trackmatch.dto.event;

import com.trackmatch.domain.enums.EventStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "O Título é obrigatório.")
    @NotBlank(message = "O Título é obrigatório.")
    private String title;

    private String description;

    @NotNull(message = "A data do evento é obrigatória.")
    private LocalDateTime date;

    @NotNull(message = "A localização do evento é obrigatória.")
    @NotBlank(message = "A localização do evento é obrigatória.")
    private String location;

    @NotNull(message = "É necessário informar o instrumento.")
    @NotBlank(message = "É necessário informar o instrumento.")
    private String instrumentNeeded;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long createdById;

    @Builder.Default
    private EventStatus status = EventStatus.OPEN;

    @Builder.Default
    private List<Long> applicationIds = new ArrayList<>();
}
