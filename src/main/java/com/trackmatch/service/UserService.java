package com.trackmatch.service;

import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.dto.user.UserDTO;
import com.trackmatch.dto.user.UserMapper;
import com.trackmatch.dto.user.UserPatchDTO;
import com.trackmatch.dto.user.UserPatchMapper;
import com.trackmatch.exception.EntityType;
import com.trackmatch.exception.NotFoundException;
import com.trackmatch.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor        // injeta dependências via construtor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPatchMapper userPatchMapper;

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.toDTOs(users);
    }

    public UserDTO getUserById(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EntityType.USER, id));
        return userMapper.toDTO(user);
    }

    public UserDTO createUser(UserDTO dto) {
        UserEntity entityToSave = userMapper.toEntity(dto);
        UserEntity saved = userRepository.save(entityToSave);
        return userMapper.toDTO(saved);
    }

    public UserDTO updateUserById(long id, UserPatchDTO userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException(EntityType.USER, id));

        userPatchMapper.updateUserFromDto(userDto, userEntity);

        userRepository.save(userEntity);

        return userMapper.toDTO(userEntity);
    }
}
