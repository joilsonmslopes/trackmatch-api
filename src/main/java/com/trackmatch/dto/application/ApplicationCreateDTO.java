package com.trackmatch.dto.application;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreateDTO {
    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "O ID do evento é obrigatório")
    private Long eventId;
}
