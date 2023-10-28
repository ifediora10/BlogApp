package com.example.demo.repositories;

import com.example.demo.models.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Boolean existsByEmail(String email);

   Optional <Admin> findByEmailAndPassword(String email, String password);
}
