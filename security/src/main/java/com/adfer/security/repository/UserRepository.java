package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findById(Integer id);
	Optional<User> findByEmail (String email);
}