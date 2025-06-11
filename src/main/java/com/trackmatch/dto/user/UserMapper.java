package com.trackmatch.dto.user;

import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    @Mapping(source = "applications", target = "applicationIds", qualifiedByName = "appId")
    @Mapping(source = "events", target = "eventIds", qualifiedByName = "eventId")
    UserResponseDTO toDTO(UserEntity userEntity);

    @Mapping(source = "applications", target = "applicationIds", qualifiedByName = "appId")
    @Mapping(source = "events", target = "eventIds", qualifiedByName = "eventId")
    List<UserResponseDTO> toDTOs(List<UserEntity> users);

    @InheritInverseConfiguration
    @Mapping(source = "applicationIds", target = "applications", qualifiedByName = "idToApplication")
    @Mapping(source = "eventIds", target = "events", qualifiedByName = "idToEvent")
    UserEntity toEntity(UserCreateDTO userCreateDTO);

    @Mapping(source = "applicationIds", target = "applications", qualifiedByName = "idToApplication")
    @Mapping(source = "eventIds", target = "events", qualifiedByName = "idToEvent")
    List<UserEntity> toEntities(List<UserCreateDTO> users);

    @Named("idToApplication")
    default ApplicationEntity idToApplication(Long id) {
        if (id == null) return null;
        ApplicationEntity app = new ApplicationEntity();
        app.setId(id);
        return app;
    }

    @Named("idToEvent")
    default EventEntity idToEvent(Long id) {
        if (id == null) return null;
        EventEntity event = new EventEntity();
        event.setId(id);
        return event;
    }

    @Named("appId")
    default Long applicationToId(ApplicationEntity app) {
        return app == null ? null : app.getId();
    }

    @Named("eventId")
    default Long eventToId(EventEntity app) {
        return app.getId();
    }
}
