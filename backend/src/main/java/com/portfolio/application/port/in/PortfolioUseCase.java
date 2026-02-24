package com.portfolio.application.port.in;

import com.portfolio.dto.*;

import java.util.List;

public interface PortfolioUseCase {
    ProfileResponse getProfile();
    List<SkillResponse> getSkills();
    List<ProjectResponse> getProjects();
    void saveContact(ContactRequest request);
    ProjectResponse createProject(ProjectRequest request);
    ProjectResponse updateProject(Long id, ProjectRequest request);
    void deleteProject(Long id);
    SkillResponse createSkill(SkillRequest request);
}
