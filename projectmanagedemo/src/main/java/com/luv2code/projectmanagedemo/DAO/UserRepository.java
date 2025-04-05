package com.luv2code.projectmanagedemo.DAO;

import com.luv2code.projectmanagedemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
