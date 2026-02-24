package com.portfolio.dto;

public record ProfileResponse(
        Long id,
        String name,
        String title,
        String summary,
        String resumeUrl,
        String githubUrl,
        String hackerrankUrl,
        String linkedinUrl
) {}
