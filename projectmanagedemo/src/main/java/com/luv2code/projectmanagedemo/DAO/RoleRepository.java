package com.luv2code.projectmanagedemo.DAO;

import com.luv2code.projectmanagedemo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
