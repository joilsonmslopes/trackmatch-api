package com.trackmatch.controller;

import com.trackmatch.dto.user.UserCreateDTO;
import com.trackmatch.dto.user.UserPatchDTO;
import com.trackmatch.dto.user.UserResponseDTO;
import com.trackmatch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userCreateDTOS = userService.getAllUsers();

        return ResponseEntity.ok(userCreateDTOS);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) throws Exception {
        UserResponseDTO userFound = userService.getUserById(id);

        return ResponseEntity.ok(userFound);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO user) {
        UserResponseDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(
            @PathVariable("id") long id,
            @RequestBody @Valid UserPatchDTO userDto) {
        UserResponseDTO user = userService.updateUserById(id, userDto);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) {
        userService.deleteUserById(id);

        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}
