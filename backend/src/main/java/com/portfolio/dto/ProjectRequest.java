package com.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String techStack,
        String githubLink,
        String liveLink
) {}
