package com.portfolio.controller;

import com.portfolio.application.port.in.PortfolioUseCase;
import com.portfolio.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicController {
    private final PortfolioUseCase portfolioUseCase;

    @GetMapping("/profile")
    public ProfileResponse profile() { return portfolioUseCase.getProfile(); }

    @GetMapping("/skills")
    public List<SkillResponse> skills() { return portfolioUseCase.getSkills(); }

    @GetMapping("/projects")
    public List<ProjectResponse> projects() { return portfolioUseCase.getProjects(); }

    @PostMapping("/contact")
    public ResponseEntity<String> contact(@Valid @RequestBody ContactRequest request) {
        portfolioUseCase.saveContact(request);
        return ResponseEntity.ok("Message submitted successfully");
    }
}
