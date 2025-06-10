package com.trackmatch.dto.event;

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
    EventResponseDTO toDTO(EventEntity eventEntity);

    @Mapping(source = "createdBy.id", target = "createdById")
    List<EventResponseDTO> toDTOs(List<EventEntity> events);

    @Mapping(source = "createdById", target = "createdBy", qualifiedByName = "mapToUser")
    @Mapping(target = "status", expression = "java(com.trackmatch.domain.enums.EventStatus.OPEN)")
    EventEntity toEntity(EventCreateDTO dto);

    @Mapping(source = "createdById", target ="createdBy", qualifiedByName = "mapToUser")
    List<EventEntity> toEntities(List<EventCreateDTO> events);

    @Named("mapToUser")
    default UserEntity mapToUser(Long id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}
