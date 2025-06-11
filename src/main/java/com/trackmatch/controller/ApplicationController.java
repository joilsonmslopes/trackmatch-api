package com.trackmatch.controller;

import com.trackmatch.dto.application.ApplicationCreateDTO;
import com.trackmatch.dto.application.ApplicationPatchDTO;
import com.trackmatch.dto.application.ApplicationResponseDTO;
import com.trackmatch.service.ApplicationService;
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

    @GetMapping("/list")
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {
        List<ApplicationResponseDTO> applications = applicationService.getAllApplications();

        return ResponseEntity.ok(applications);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable("id") Long id) {
        ApplicationResponseDTO applicationFound = applicationService.getApplicationById(id);

        return ResponseEntity.ok(applicationFound);
    }

    @PostMapping("/create")
    public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody @Valid ApplicationCreateDTO dto) {
        ApplicationResponseDTO applicationResponseDTO = applicationService.createApplication(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(applicationResponseDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateApplicationStatusById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ApplicationPatchDTO dto) {
        ApplicationResponseDTO applicationResponseDTO = applicationService.updateApplicationById(id, dto);

        return ResponseEntity.ok(applicationResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplicationById(@PathVariable("id") Long id) {
        applicationService.deleteApplicationById(id);

        return ResponseEntity.ok("Aplicação de evento com ID: " + id + " deletado com sucesso!");
    }
}
