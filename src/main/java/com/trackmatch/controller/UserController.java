package com.trackmatch.controller;

import com.trackmatch.dto.user.UserCreateDTO;
import com.trackmatch.dto.user.UserPatchDTO;
import com.trackmatch.dto.user.UserResponseDTO;
import com.trackmatch.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
            summary     = "Listar todos os usuários",
            description = "Retorna uma lista paginada (e opcionalmente filtrada) de todos os usuários cadastrados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de consulta inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userCreateDTOS = userService.getAllUsers();

        return ResponseEntity.ok(userCreateDTOS);
    }

    @Operation(
            summary     = "Buscar usuário por ID",
            description = "Retorna os detalhes completos do usuário identificado pelo **ID**."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido (formato incorreto)"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) throws Exception {
        UserResponseDTO userFound = userService.getUserById(id);

        return ResponseEntity.ok(userFound);
    }

    @Operation(
            summary     = "Criar um novo usuário",
            description = "Registra um usuário a partir do corpo da requisição (**JSON**). "
                    + "Retorna **201 Created** com o objeto persistido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do usuário inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail já cadastrado ou outro dado único"),
            @ApiResponse(responseCode = "422", description = "Regras de validação não atendidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO user) {
        UserResponseDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(
            summary     = "Atualizar usuário por ID",
            description = "Atualiza parcialmente (PATCH) o usuário especificado pelo **ID** e devolve o objeto atualizado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do usuário ou ID inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito ao atualizar (ex.: e-mail duplicado)"),
            @ApiResponse(responseCode = "422", description = "Regras de validação não atendidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
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
            summary     = "Excluir usuário por ID",
            description = "Remove o usuário identificado pelo **ID**. Em caso de sucesso devolve **204 No Content**."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido (formato incorreto)"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito: não foi possível excluir (dependências)"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) {
        userService.deleteUserById(id);

        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}
