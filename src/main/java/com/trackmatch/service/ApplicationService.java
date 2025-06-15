package com.trackmatch.service;

import com.trackmatch.domain.entities.ApplicationEntity;
import com.trackmatch.domain.entities.EventEntity;
import com.trackmatch.domain.entities.UserEntity;
import com.trackmatch.domain.enums.ApplicationStatus;
import com.trackmatch.dto.application.*;
import com.trackmatch.exception.EntityType;
import com.trackmatch.exception.NotFoundException;
import com.trackmatch.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ApplicationPatchMapper applicationPatchMapper;
    private final UserService userService;
    private final EventService eventService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            ApplicationMapper applicationMapper,
            ApplicationPatchMapper applicationPatchMapper,
            UserService userService,
            EventService eventService) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.applicationPatchMapper = applicationPatchMapper;
        this.userService = userService;
        this.eventService = eventService;
    }

    public List<ApplicationResponseDTO> getAllApplications() {
        List<ApplicationEntity> applications = applicationRepository.findAll();

        return applicationMapper.toDTOs(applications);
    }

    public ApplicationResponseDTO getApplicationById(Long id) {
        ApplicationEntity application = applicationRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityType.APPLICATION, id));

        return applicationMapper.toDTO(application);
    }

    public ApplicationResponseDTO createApplication(ApplicationCreateDTO dto) {
        UserEntity user = userService.getUserEntityById(dto.getUserId());
        EventEntity event = eventService.getEventEntityById(dto.getEventId());

        ApplicationEntity applicationEntity = applicationMapper.toEntity(dto);

        applicationEntity.setUser(user);
        applicationEntity.setEvent(event);
        applicationEntity.setStatus(ApplicationStatus.PENDING);
        applicationEntity.setCreatedAt(LocalDateTime.now());

        applicationRepository.save(applicationEntity);

        return applicationMapper.toDTO(applicationEntity);
    }

    public ApplicationResponseDTO updateApplicationById(Long id, ApplicationPatchDTO dto) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityType.APPLICATION, id));

        applicationPatchMapper.updateApplicationFromDto(dto, applicationEntity);

        applicationRepository.save(applicationEntity);

        return applicationMapper.toDTO(applicationEntity);
    }

    public void deleteApplicationById(Long id) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EntityType.APPLICATION, id));
        
        applicationRepository.deleteById(id);
    }
}
