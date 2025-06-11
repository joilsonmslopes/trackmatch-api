package com.trackmatch.dto.application;

import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ApplicationMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    ApplicationResponseDTO toDTO(ApplicationEntity entity);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    List<ApplicationResponseDTO> toDTOs(List<ApplicationEntity> entities);

    @Mapping(source = "userId", target = "user", qualifiedByName = "mapToUser")
    @Mapping(source = "eventId", target = "event", qualifiedByName = "mapToEvent")
    ApplicationEntity toEntity(ApplicationCreateDTO dto);

    @Mapping(source = "userId", target = "user", qualifiedByName = "mapToUser")
    @Mapping(source = "eventId", target = "event", qualifiedByName = "mapToEvent")
    List<ApplicationEntity> toEntities(List<ApplicationCreateDTO> applicationDTOs);

    @Named("mapToUser")
    default UserEntity mapToUser(Long id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

    @Named("mapToEvent")
    default EventEntity mapToEvent(Long id) {
        if (id == null) return null;
        EventEntity event = new EventEntity();
        event.setId(id);
        return event;
    }
}
