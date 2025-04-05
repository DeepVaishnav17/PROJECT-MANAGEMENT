package com.luv2code.projectmanagedemo.Service;

import com.luv2code.projectmanagedemo.DAO.DepartmentRepository;
import com.luv2code.projectmanagedemo.DAO.UserRepository;
import com.luv2code.projectmanagedemo.Entity.Department;
import com.luv2code.projectmanagedemo.Entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentService(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Department createDepartment(Department department) {

        List<User> updatedUsers = new ArrayList<>();

        if (department.getUsers() != null) {
            for (User incomingUser : department.getUsers()) {

                User userFromDb = userRepository.findById(incomingUser.getId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + incomingUser.getId()));


                userFromDb.setDepartment(department);

                updatedUsers.add(userFromDb);
            }
        }

        department.setUsers(updatedUsers);

        return departmentRepository.save(department);
    }



    @Transactional
    public Department updateDepartment(Department department, Long departmentId) {

        Department existingDept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));


        existingDept.setName(department.getName());

        if (department.getUsers() != null) {
            List<User> updatedUsers = new ArrayList<>();

            for (User partialUser : department.getUsers()) {
                User userFromDb = userRepository.findById(partialUser.getId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + partialUser.getId()));


                userFromDb.setDepartment(existingDept);

                updatedUsers.add(userFromDb);
            }

            existingDept.setUsers(updatedUsers);
        }

        return departmentRepository.save(existingDept);
    }


    // Get Department by ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    // Get all Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Delete Department by ID
    @Transactional
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
