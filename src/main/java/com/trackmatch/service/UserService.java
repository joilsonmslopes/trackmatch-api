package com.trackmatch.service;

import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.dto.user.UserDTO;
import com.trackmatch.dto.user.UserMapper;
import com.trackmatch.exception.EntityType;
import com.trackmatch.exception.NotFoundException;
import com.trackmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (UserEntity userEntity : userEntities) {
            userDTOs.add(userMapper.map(userEntity));
        }

        return userDTOs;
    }

    public UserDTO getUserById(long id) {
        UserEntity userFound = userRepository.findById(id).orElseThrow(() -> new NotFoundException(EntityType.USER, id));

        return userMapper.map(userFound);
    }

    public UserDTO createUser(UserDTO user) {
        UserEntity userEntity = userMapper.map(user);
        userEntity = userRepository.save(userEntity);

        return userMapper.map(userEntity);
    }

}
