package com.trackmatch.service;

import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.dto.event.*;
import com.trackmatch.dto.user.UserResponseDTO;
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
    private final UserService userService;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, EventUpdateMapper eventUpdateMapper, UserService userService) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.eventUpdateMapper = eventUpdateMapper;
        this.userService = userService;
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

    public EventEntity getEventEntityById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(EntityType.EVENT, id));
    }

    public EventResponseDTO createEvent(EventCreateDTO eventDto) {
        UserEntity userEntity = userService.getUserEntityById(eventDto.getCreatedById());

        EventEntity eventEntity = eventMapper.toEntity(eventDto);

        eventEntity.setCreatedBy(userEntity);

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
