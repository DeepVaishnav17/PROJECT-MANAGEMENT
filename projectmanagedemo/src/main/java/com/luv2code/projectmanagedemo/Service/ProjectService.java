package com.luv2code.projectmanagedemo.Service;

import com.luv2code.projectmanagedemo.DAO.ProjectRepository;
import com.luv2code.projectmanagedemo.Entity.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Create Project
    @Transactional
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Update Project
    @Transactional
    public Project updateProject(Project project, Long projectId) {
        Project existingProject = getProjectById(projectId);
        existingProject.setProjectName(project.getProjectName());
        existingProject.setUsers(project.getUsers());
        return projectRepository.save(existingProject);
    }

    // Get Project by ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    // Get all Projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Delete Project by ID
    @Transactional
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
