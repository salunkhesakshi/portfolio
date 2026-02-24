package com.portfolio.application.service;

import com.portfolio.application.port.in.PortfolioUseCase;
import com.portfolio.dto.*;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.infrastructure.persistence.entity.*;
import com.portfolio.infrastructure.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService implements PortfolioUseCase {
    private final ProfileJpaRepository profileRepo;
    private final SkillJpaRepository skillRepo;
    private final ProjectJpaRepository projectRepo;
    private final ContactJpaRepository contactRepo;

    @Override
    public ProfileResponse getProfile() {
        ProfileEntity p = profileRepo.findAll().stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Profile not configured"));
        return new ProfileResponse(p.getId(), p.getName(), p.getTitle(), p.getSummary(), p.getResumeUrl(), p.getGithubUrl(), p.getHackerrankUrl(), p.getLinkedinUrl());
    }

    @Override
    public List<SkillResponse> getSkills() {
        return skillRepo.findAll().stream()
                .sorted(Comparator.comparing(SkillEntity::getCategory).thenComparing(SkillEntity::getName))
                .map(s -> new SkillResponse(s.getId(), s.getName(), s.getCategory())).toList();
    }

    @Override
    public List<ProjectResponse> getProjects() {
        return projectRepo.findAll().stream()
                .map(p -> new ProjectResponse(p.getId(), p.getTitle(), p.getDescription(), p.getTechStack(), p.getGithubLink(), p.getLiveLink())).toList();
    }

    @Override
    public void saveContact(ContactRequest request) {
        contactRepo.save(ContactEntity.builder().name(request.name()).email(request.email()).message(request.message()).createdAt(Instant.now()).build());
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        ProjectEntity p = projectRepo.save(ProjectEntity.builder().title(request.title()).description(request.description()).techStack(request.techStack()).githubLink(request.githubLink()).liveLink(request.liveLink()).build());
        return new ProjectResponse(p.getId(), p.getTitle(), p.getDescription(), p.getTechStack(), p.getGithubLink(), p.getLiveLink());
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        ProjectEntity p = projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        p.setTitle(request.title()); p.setDescription(request.description()); p.setTechStack(request.techStack()); p.setGithubLink(request.githubLink()); p.setLiveLink(request.liveLink());
        p = projectRepo.save(p);
        return new ProjectResponse(p.getId(), p.getTitle(), p.getDescription(), p.getTechStack(), p.getGithubLink(), p.getLiveLink());
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepo.existsById(id)) throw new ResourceNotFoundException("Project not found");
        projectRepo.deleteById(id);
    }

    @Override
    public SkillResponse createSkill(SkillRequest request) {
        SkillEntity s = skillRepo.save(SkillEntity.builder().name(request.name()).category(request.category()).build());
        return new SkillResponse(s.getId(), s.getName(), s.getCategory());
    }
}
