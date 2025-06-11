package com.trackmatch.service;

import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.dto.user.*;
import com.trackmatch.exception.EntityType;
import com.trackmatch.exception.NotFoundException;
import com.trackmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPatchMapper userPatchMapper;

    public List<UserResponseDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.toDTOs(users);
    }

    public UserResponseDTO getUserById(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EntityType.USER, id));
        return userMapper.toDTO(user);
    }

    public UserResponseDTO createUser(UserCreateDTO dto) {
        UserEntity entityToSave = userMapper.toEntity(dto);
        UserEntity saved = userRepository.save(entityToSave);
        return userMapper.toDTO(saved);
    }

    public UserResponseDTO updateUserById(long id, UserPatchDTO userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException(EntityType.USER, id));

        userPatchMapper.updateUserFromDto(userDto, userEntity);

        userRepository.save(userEntity);

        return userMapper.toDTO(userEntity);
    }

    public void deleteUserById(long id) {
        UserEntity userFound = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityType.USER, id));

        userRepository.deleteById(userFound.getId());
    }
}
