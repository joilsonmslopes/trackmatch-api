package com.trackmatch.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackmatch.domain.enums.ProfileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfileType profileType;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String instruments;

    private String styles;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String bio;

    private Boolean active = true;

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<EventEntity> events = new ArrayList<>();
}
