package com.trackmatch.controller;

import com.trackmatch.dto.application.ApplicationCreateDTO;
import com.trackmatch.dto.application.ApplicationPatchDTO;
import com.trackmatch.dto.application.ApplicationResponseDTO;
import com.trackmatch.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Operation(
            summary     = "Listar todas as candidaturas",
            description = "Retorna uma lista paginada (e opcionalmente filtrada) de todas as candidaturas (applications) registradas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de consulta inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @GetMapping("/list")
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        List<ApplicationResponseDTO> applications = applicationService.getAllApplications();

        return ResponseEntity.ok(applications);
    }

    @Operation(
            summary     = "Buscar candidatura por ID",
            description = "Retorna os detalhes completos da candidatura identificada pelo parâmetro **ID**."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidatura encontrada"),
            @ApiResponse(responseCode = "400", description = "ID inválido (formato incorreto)"),
            @ApiResponse(responseCode = "404", description = "Candidatura não encontrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable("id") Long id) {
        ApplicationResponseDTO applicationFound = applicationService.getApplicationById(id);

        return ResponseEntity.ok(applicationFound);
    }

    @Operation(
            summary     = "Criar uma nova candidatura",
            description = "Registra uma candidatura a partir do corpo da requisição (**JSON**), normalmente contendo `eventId` e `userId`. "
                    + "Retorna **201 Created** com o objeto persistido."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Candidatura criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da candidatura inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito: já existe candidatura semelhante"),
            @ApiResponse(responseCode = "422", description = "Regras de validação não atendidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @PostMapping("/create")
    public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody @Valid ApplicationCreateDTO dto) {
        ApplicationResponseDTO applicationResponseDTO = applicationService.createApplication(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponseDTO);
    }

    @Operation(
            summary     = "Atualizar candidatura por ID",
            description = "Atualiza completamente (PUT) ou parcialmente (PATCH) a candidatura especificada pelo **ID** e devolve o objeto atualizado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidatura atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da candidatura ou ID inválidos"),
            @ApiResponse(responseCode = "404", description = "Candidatura não encontrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito ao atualizar candidatura"),
            @ApiResponse(responseCode = "422", description = "Regras de validação não atendidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateApplicationStatusById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ApplicationPatchDTO dto) {
        ApplicationResponseDTO applicationResponseDTO = applicationService.updateApplicationById(id, dto);

        return ResponseEntity.ok(applicationResponseDTO);
    }

    @Operation(
            summary     = "Excluir candidatura por ID",
            description = "Remove a candidatura identificada pelo **ID**. Em caso de sucesso devolve **204 No Content**."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Candidatura removida com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido (formato incorreto)"),
            @ApiResponse(responseCode = "404", description = "Candidatura não encontrada"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito: não foi possível excluir (dependências)"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplicationById(@PathVariable("id") Long id) {
        applicationService.deleteApplicationById(id);

        return ResponseEntity.ok("Aplicação de evento com ID: " + id + " deletado com sucesso!");
    }
}
