package com.trackmatch.service;

import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.dto.user.UserDTO;
import com.trackmatch.dto.user.UserMapper;
import com.trackmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
