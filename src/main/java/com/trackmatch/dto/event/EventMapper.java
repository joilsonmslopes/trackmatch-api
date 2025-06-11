package com.trackmatch.dto.event;

import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import org.mapstruct.*;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EventMapper {
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "applications", target = "applicationIds", qualifiedByName = "appId")
    EventResponseDTO toDTO(EventEntity eventEntity);

    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "applications", target = "applicationIds", qualifiedByName = "appId")
    List<EventResponseDTO> toDTOs(List<EventEntity> events);

    @Mapping(source = "createdById", target = "createdBy", qualifiedByName = "mapToUser")
    @Mapping(target = "status", expression = "java(com.trackmatch.domain.enums.EventStatus.OPEN)")
    @Mapping(source = "applicationIds", target = "applications", qualifiedByName = "idToApplication")
    EventEntity toEntity(EventCreateDTO dto);

    @Mapping(source = "createdById", target ="createdBy", qualifiedByName = "mapToUser")
    @Mapping(source = "applicationIds", target = "applications", qualifiedByName = "idToApplication")
    List<EventEntity> toEntities(List<EventCreateDTO> events);

    @Named("mapToUser")
    default UserEntity mapToUser(Long id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

    @Named("idToApplication")
    default ApplicationEntity idToApplication(Long id) {
        if (id == null) return null;
        ApplicationEntity app = new ApplicationEntity();
        app.setId(id);
        return app;
    }

    @Named("appId")
    default Long applicationToId(ApplicationEntity app) {
        return app == null ? null : app.getId();
    }
}
