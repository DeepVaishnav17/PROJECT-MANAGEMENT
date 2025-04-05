package com.luv2code.projectmanagedemo.Service;

import com.luv2code.projectmanagedemo.DAO.RoleRepository;
import com.luv2code.projectmanagedemo.Entity.Role;
import com.luv2code.projectmanagedemo.Entity.User;
import com.luv2code.projectmanagedemo.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository
                    , RoleRepository roleRepository, PasswordEncoder passwordEncoder
    ) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Transactional
    public User updateUser(User updatedUser, Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));


        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getProjects() != null) {
            existingUser.setProjects(updatedUser.getProjects());
        }
        if (updatedUser.getRoles() != null) {
            List<Role> rolesFromDb = new ArrayList<>();
            for (Role r : updatedUser.getRoles()) {
                Role role = roleRepository.findById(r.getId())
                        .orElseThrow(() -> new RuntimeException("Role not found with id: " + r.getId()));
                role.setUser(existingUser);
                rolesFromDb.add(role);
            }
            existingUser.setRoles(rolesFromDb);
        }


        return userRepository.save(existingUser);
    }



    // Get User by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete User by ID
    @Transactional
    public void deleteUserById(Long id) {

        User user = getUserById(id);
        for (Role r : user.getRoles()) {
            r.setUser(null); // break FOREIGN KEY constraint
        }

        userRepository.deleteById(id);
    }



    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id " + userId);
        }
        User user = getUserById(userId);


        for (Role r : user.getRoles()) {
            r.setUser(null);
            roleRepository.delete(r);
        }
        userRepository.deleteById(userId);
    }


    @Transactional
    public User assignRole(Long userId, Role role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        user.getRoles().add(role);
        role.setUser(user);
        roleRepository.save(role);
        return userRepository.save(user);
    }



    @Transactional
    public User removeRole(Long userId, Long roleId) {
        return userRepository.findById(userId)
                .map(user -> {
                    Role roleToRemove = user.getRoles().stream()
                            .filter(role -> role.getId().equals(roleId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));


                    user.getRoles().remove(roleToRemove);
                    roleToRemove.setUser(null);

                    roleRepository.delete(roleToRemove);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
    }
}
