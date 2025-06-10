package com.trackmatch.service;

import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.dto.event.*;
import com.trackmatch.exception.EntityType;
import com.trackmatch.exception.NotFoundException;
import com.trackmatch.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventUpdateMapper eventUpdateMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, EventUpdateMapper eventUpdateMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.eventUpdateMapper = eventUpdateMapper;
    }

    public List<EventResponseDTO> getAllEvents() {
        List<EventEntity> eventEntities = eventRepository.findAll();

        return eventMapper.toDTOs(eventEntities);
    }

    public EventResponseDTO getEventById(Long id) {
        EventEntity eventFound = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityType.EVENT, id));

        return eventMapper.toDTO(eventFound);
    }

    public EventResponseDTO createEvent(EventCreateDTO eventDto) {
        EventEntity eventEntity = eventMapper.toEntity(eventDto);

        eventEntity = eventRepository.save(eventEntity);

        return eventMapper.toDTO(eventEntity);
    }

    public EventResponseDTO updateEventById(Long id, EventUpdateDTO dto) {
        EventEntity eventEntity = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityType.EVENT, id));

        eventUpdateMapper.updateEventFromDto(dto, eventEntity);

        eventRepository.save(eventEntity);

        return eventMapper.toDTO(eventEntity);
    }

    public void deleteEventById(Long id) {
        EventEntity eventEntity = eventRepository
                .findById(id).orElseThrow(() -> new NotFoundException(EntityType.EVENT, id));

        eventRepository.deleteById(id);
    }
}
