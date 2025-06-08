package com.trackmatch.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackmatch.domain.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String instrumentNeeded;

    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<ApplicationEntity> applications = new ArrayList<>();
}
