package com.trackmatch.controller;

import com.trackmatch.dto.event.EventCreateDTO;
import com.trackmatch.dto.event.EventResponseDTO;
import com.trackmatch.dto.event.EventUpdateDTO;
import com.trackmatch.service.EventService;
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

    @GetMapping("/list")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable("id") Long id) {
        EventResponseDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/create")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody @Valid EventCreateDTO event) {
        EventResponseDTO eventResponse = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable("id") Long id,
                                                        @RequestBody @Valid EventUpdateDTO eventDto) {
        EventResponseDTO eventResponse = eventService.updateEventById(id, eventDto);

        return ResponseEntity.ok(eventResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);

        return ResponseEntity.ok("Evento com o ID: " + id + " deletado com sucesso!");
    }
}
