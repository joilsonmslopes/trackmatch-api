package com.trackmatch.controller;

import com.trackmatch.dto.user.UserDTO;
import com.trackmatch.dto.user.UserPatchDTO;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();

        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) throws Exception {
        UserDTO userFound = userService.getUserById(id);

        return ResponseEntity.ok(userFound);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user) {
        UserDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUserById(
            @PathVariable("id") long id,
            @RequestBody @Valid UserPatchDTO userDto) {
        UserDTO user = userService.updateUserById(id, userDto);

        return ResponseEntity.ok(user);
    }
}
