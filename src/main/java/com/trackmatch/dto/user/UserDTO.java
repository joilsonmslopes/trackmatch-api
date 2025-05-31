package com.trackmatch.dto.user;

import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private ProfileType profileType;
    private String phone;
    private String instruments;
    private String styles;
    private String city;
    private String state;
    private String bio;
    private List<EventEntity> events = new ArrayList<>();
}
