package com.luv2code.projectmanagedemo.Service;

import com.luv2code.projectmanagedemo.DAO.RoleRepository;
import com.luv2code.projectmanagedemo.DAO.UserRepository;
import com.luv2code.projectmanagedemo.Entity.Role;
import com.luv2code.projectmanagedemo.Entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    // Create Role
    @Transactional
    public Role createRole(Role role) {

        return roleRepository.save(role);
    }

    // Update Role
    @Transactional
    public Role updateRole(Role role, Long roleId) {
        return roleRepository.save(role);
    }

    // Get Role by ID
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    // Get all Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Delete Role by ID
    @Transactional
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
