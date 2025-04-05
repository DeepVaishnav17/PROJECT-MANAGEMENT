package com.luv2code.projectmanagedemo.RestController;

import com.luv2code.projectmanagedemo.Entity.Role;
import com.luv2code.projectmanagedemo.Entity.User;
import com.luv2code.projectmanagedemo.Service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Create Role
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    // Get All Roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    // Get Role by ID
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    // Update Role
    @PutMapping("/{id}")
    public Role updateRole(@RequestBody Role role, @PathVariable Long id) {
        role.setId(id);
        return roleService.updateRole(role, id);
    }

    // Delete Role
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}
