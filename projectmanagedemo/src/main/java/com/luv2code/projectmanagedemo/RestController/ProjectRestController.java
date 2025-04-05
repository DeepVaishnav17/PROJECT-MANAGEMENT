package com.luv2code.projectmanagedemo.RestController;

import com.luv2code.projectmanagedemo.Entity.Project;
import com.luv2code.projectmanagedemo.Service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create Project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    // Get All Projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Get Project by ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // Update Project
    @PutMapping("/{id}")
    public Project updateProject(@RequestBody Project project, @PathVariable Long id) {
        project.setId(id);
        return projectService.updateProject(project, id);
    }

    // Delete Project
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
    }
}
