package com.portfolio.config;

import com.portfolio.infrastructure.persistence.entity.*;
import com.portfolio.infrastructure.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {
    private final UserJpaRepository userRepo;
    private final ProfileJpaRepository profileRepo;
    private final SkillJpaRepository skillRepo;
    private final ProjectJpaRepository projectRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepo.count() == 0) {
            userRepo.save(UserEntity.builder().email("admin@portfolio.dev").password(passwordEncoder.encode("admin123")).role(Role.ADMIN).build());
        }
        if (profileRepo.count() == 0) {
            profileRepo.save(ProfileEntity.builder().name("Your Name").title("Software Developer").summary("I build production-ready full-stack applications.")
                    .resumeUrl("https://example.com/resume.pdf").githubUrl("https://github.com/yourusername")
                    .hackerrankUrl("https://hackerrank.com/yourusername").linkedinUrl("https://linkedin.com/in/yourusername").build());
        }
        if (skillRepo.count() == 0) {
            skillRepo.save(SkillEntity.builder().name("Java").category("Backend").build());
            skillRepo.save(SkillEntity.builder().name("Spring Boot").category("Backend").build());
            skillRepo.save(SkillEntity.builder().name("React").category("Frontend").build());
            skillRepo.save(SkillEntity.builder().name("PostgreSQL").category("Databases").build());
            skillRepo.save(SkillEntity.builder().name("Docker").category("DevOps/Cloud").build());
        }
        if (projectRepo.count() == 0) {
            projectRepo.save(ProjectEntity.builder().title("Portfolio Platform").description("Production-grade developer portfolio.")
                    .techStack("React, Spring Boot, PostgreSQL, Docker")
                    .githubLink("https://github.com/yourusername/portfolio")
                    .liveLink("https://portfolio.example.com").build());
        }
        log.info("Initial seed data loaded");
    }
}
