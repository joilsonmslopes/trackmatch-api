package com.trackmatch.dto.user;

import com.trackmatch.domain.enums.ProfileType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private ProfileType profileType;
    private String phone;
    private String instruments;
    private String styles;
    private String city;
    private boolean active;
    private String state;
    private String bio;

    @Builder.Default
    private List<Long> eventIds = new ArrayList<>();

    @Builder.Default
    private List<Long> applicationIds = new ArrayList<>();
}
