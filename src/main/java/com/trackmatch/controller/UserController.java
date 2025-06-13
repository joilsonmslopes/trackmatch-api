package com.trackmatch.controller;

import com.trackmatch.dto.user.UserCreateDTO;
import com.trackmatch.dto.user.UserPatchDTO;
import com.trackmatch.dto.user.UserResponseDTO;
import com.trackmatch.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Rota para listagem de todos os usuário.",
            description = "Essa rota lista todos os usuário cadastrados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso.")
    })
    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userCreateDTOS = userService.getAllUsers();

        return ResponseEntity.ok(userCreateDTOS);
    }

    @Operation(
            summary = "Rota para buscar um usuário por (id)",
            description = "Essa rota retorna um usuário específico com base no (id) informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) throws Exception {
        UserResponseDTO userFound = userService.getUserById(id);

        return ResponseEntity.ok(userFound);
    }

    @Operation(
            summary = "Rota para cadastro de um usuário",
            description = "Essa rota é para criação de um novo usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Usuário cadastrado.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserCreateDTO.class))
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO user) {
        UserResponseDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(
            summary = "Rota para atualizar usuário pelo (id)",
            description = "Essa rota permite atualizar os dados de um usuário existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizado do usuário.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserPatchDTO.class))
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PatchMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(
            @Parameter(description = "Id do usuário a ser atualizado.", required = true)
            @PathVariable("id") long id,
            @RequestBody @Valid UserPatchDTO userDto) {
        UserResponseDTO user = userService.updateUserById(id, userDto);

        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Rota para deletar um usuário pelo (id)",
            description = "Essa rota permite excluir um usuário com base no (id) informado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) {
        userService.deleteUserById(id);

        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}
