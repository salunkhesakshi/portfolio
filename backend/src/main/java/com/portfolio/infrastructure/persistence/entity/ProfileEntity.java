package com.portfolio.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profile")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfileEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String summary;
    @Column(name = "resume_url")
    private String resumeUrl;
    @Column(name = "github_url")
    private String githubUrl;
    @Column(name = "hackerrank_url")
    private String hackerrankUrl;
    @Column(name = "linkedin_url")
    private String linkedinUrl;
}
