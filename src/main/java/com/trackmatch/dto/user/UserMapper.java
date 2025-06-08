package com.trackmatch.dto.user;

import com.trackmatch.domain.entities.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",            // vira um bean do Spring
        unmappedTargetPolicy = ReportingPolicy.IGNORE // silencia avisos de campos não mapeados
)
public interface UserMapper {

    // ---------- Entity ➜ DTO ----------
    UserDTO toDTO(UserEntity userEntity);

    // Lista de entidades ➜ lista de DTOs
    List<UserDTO> toDTOs(List<UserEntity> users);

    // ---------- DTO ➜ Entity ----------
    @InheritInverseConfiguration
    UserEntity toEntity(UserDTO userDTO);

    // Lista de DTOs ➜ lista de entidades
    List<UserEntity> toEntities(List<UserDTO> users);
}
