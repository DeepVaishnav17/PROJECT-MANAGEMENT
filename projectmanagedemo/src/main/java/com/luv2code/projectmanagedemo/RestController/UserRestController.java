package com.luv2code.projectmanagedemo.RestController;

import com.luv2code.projectmanagedemo.Entity.Role;
import com.luv2code.projectmanagedemo.Entity.User;
import com.luv2code.projectmanagedemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private final UserService userService;

    public UserRestController(UserService userService) {

        this.userService = userService;
    }

    // Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        user.setId(id);
        return userService.updateUser(user, id);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUserById(id);
    }

    //EXPLICITLY ADDING ROLES
    @PostMapping("/{id}/roles")
    public User assignRole(@PathVariable("id") Long userId, @RequestBody Role role) {
        return userService.assignRole(userId, role);
    }

    @DeleteMapping("/{id}/roles/{roleId}")
    public User removeRole(@PathVariable("id") Long userId, @PathVariable("roleId") Long roleId) {
        return userService.removeRole(userId, roleId);
    }
}
