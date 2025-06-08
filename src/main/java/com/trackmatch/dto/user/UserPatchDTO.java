package com.trackmatch.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trackmatch.domain.enums.ProfileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class UserPatchDTO {
    private String name;
    private String email;
    private String password;
    private ProfileType profileType;
    private String phone;
    private String instruments;
    private String styles;
    private String city;
    private Boolean active = true;
    private String state;
    private String bio;
}
