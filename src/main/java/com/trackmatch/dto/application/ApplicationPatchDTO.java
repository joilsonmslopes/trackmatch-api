package com.trackmatch.dto.application;

import com.trackmatch.domain.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationPatchDTO {
    private ApplicationStatus status;
}
