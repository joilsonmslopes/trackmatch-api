package com.trackmatch.controller;

import com.trackmatch.dto.event.EventCreateDTO;
import com.trackmatch.dto.event.EventResponseDTO;
import com.trackmatch.dto.event.EventUpdateDTO;
import com.trackmatch.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(
            summary = "Listar todos os eventos",
            description = "Retorna uma lista paginada (e opcionalmente filtrada) de todos os eventos cadastrados no sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description  = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "400", description  = "Parâmetros de consulta inválidos"),
            @ApiResponse(responseCode = "401", description  = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description  = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "500", description  = "Erro interno inesperado")
    })
    @GetMapping("/list")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @Operation(
            summary = "Buscar evento por ID",
            description = "Retorna os detalhes completos do evento identificado pelo parâmetro **ID**. "
                    + "Se o ID não existir, responde com **404 Not Found**."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido (formato incorreto)"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable("id") Long id) {
        EventResponseDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @Operation(
            summary = "Criar um novo evento",
            description = "Cadastra um evento a partir do corpo da requisição (**JSON**). "
                    + "Responde com **201 Created** e o objeto persistido quando bem-sucedido."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Evento criado com sucesso!",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EventResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Dados do evento inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito: já existe evento com dados conflitantes"),
            @ApiResponse(responseCode = "422", description = "Regras de validação não atendidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")

    })
    @PostMapping("/create")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody @Valid EventCreateDTO event) {
        EventResponseDTO eventResponse = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }

    @Operation(
            summary = "Atualizar evento por ID",
            description = "Atualiza parcialmente (PATCH) o evento especificado pelo **ID**. "
                    + "Retorna o evento atualizado ou **404 Not Found** se o ID não existir."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do evento ou ID inválidos"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito ao atualizar evento"),
            @ApiResponse(responseCode = "422", description = "Regras de validação não atendidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @PatchMapping("/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable("id") Long id,
                                                        @RequestBody @Valid EventUpdateDTO eventDto) {
        EventResponseDTO eventResponse = eventService.updateEventById(id, eventDto);

        return ResponseEntity.ok(eventResponse);
    }

    @Operation(
            summary = "Excluir evento por ID",
            description = "Remove o evento identificado pelo **ID** e devolve **204 No Content** em caso de sucesso. "
                    + "Se o evento não for encontrado, devolve **404 Not Found**."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Evento removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido (formato incorreto)"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "403", description = "Usuário não possui permissão"),
            @ApiResponse(responseCode = "409", description = "Conflito: não foi possível excluir (dependências)"),
            @ApiResponse(responseCode = "500", description = "Erro interno inesperado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);

        return ResponseEntity.ok("Evento com o ID: " + id + " deletado com sucesso!");
    }
}
