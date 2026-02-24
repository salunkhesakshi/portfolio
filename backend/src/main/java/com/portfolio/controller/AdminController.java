package com.portfolio.controller;

import com.portfolio.application.port.in.PortfolioUseCase;
import com.portfolio.dto.ProjectRequest;
import com.portfolio.dto.ProjectResponse;
import com.portfolio.dto.SkillRequest;
import com.portfolio.dto.SkillResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PortfolioUseCase portfolioUseCase;

    @PostMapping("/project")
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(portfolioUseCase.createProject(request));
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(portfolioUseCase.updateProject(id, request));
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        portfolioUseCase.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/skill")
    public ResponseEntity<SkillResponse> createSkill(@Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(portfolioUseCase.createSkill(request));
    }
}
