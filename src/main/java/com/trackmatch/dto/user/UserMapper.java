package com.trackmatch.dto.user;

import com.trackmatch.domain.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO map(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setBio(userEntity.getBio());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setCity(userEntity.getCity());
        userDTO.setState(userEntity.getState());
        userDTO.setInstruments(userEntity.getInstruments());
        userDTO.setStyles(userEntity.getStyles());
        userDTO.setProfileType(userEntity.getProfileType());
        userDTO.setEvents(userEntity.getEvents());

        return userDTO;
    }

    public UserEntity map(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setBio(userDTO.getBio());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setCity(userDTO.getCity());
        userEntity.setState(userDTO.getState());
        userEntity.setInstruments(userDTO.getInstruments());
        userEntity.setStyles(userDTO.getStyles());
        userEntity.setProfileType(userDTO.getProfileType());
        userEntity.setEvents(userDTO.getEvents());

        return userEntity;
    }
}
