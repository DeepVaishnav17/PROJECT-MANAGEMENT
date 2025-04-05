package com.luv2code.projectmanagedemo.DAO;

import com.luv2code.projectmanagedemo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
